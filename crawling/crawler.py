import csv
import selenium
import pymysql
import time
import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.dialects.mysql import insert
from selenium.common.exceptions import NoSuchElementException
from selenium import webdriver
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from datetime import datetime

########## 시작 함수 ##########
def startCrawling(year, category):
    print("--------" + year + "년 " + category + " 크롤링 시작 --------")
    sub_categories = []
    if category == "000001":
        sub_categories = sub_category_01
    elif category == "000002":
        sub_categories = sub_category_02

    for i in sub_categories:
        # 1페이지로 이동
        driver.get(list_url.format(1, category, i, year))

        # 총 페이지 수 추출
        pageNum = driver.find_element_by_xpath(
            "/html/body/div[1]/div[2]/div[2]/table/tbody/tr[11]/td/table/tbody/tr[35]/td"
        ).text
        pageNum = pageNum.split("/")[1]
        pageNum = pageNum[0 : len(pageNum) - 1]

        # 페이지 별 공연 ID 크롤링
        for i in range(1, int(pageNum) + 1):
            showList(i, category, i, year)

    print("--------" + year + "년 " + " 공연 목록 크롤링 완료 --------")

    # 먼저 호출
    makePerformance(year, category)

    # for i in show_list:
    #     showDetail(i, category)
    # print("--------" + year + "년 " + category + " 공연 상세 크롤링 완료 --------")

    # makeSeason()
    # makeActor()
    # makeCasting()


#### 공연 테이블 생성 함수 ####
def makePerformance(year, category):

    df = pd.DataFrame(show_list)
    df.to_csv(
        f"./data/showlist_{category}_{year}.csv",
        mode="w",
        encoding="utf-8-sig",
        header=True,
        index=False,
    )

    show_list.clear()

    # df = pd.DataFrame(performance_set, columns=["performance_name"])
    # df.insert(0, "id", None)
    # df.insert(1, "last_season_id", None)
    # df.insert(len(df.columns), "performance_image", None)
    # df.insert(len(df.columns), "performance_type", category)
    # df.insert(len(df.columns), "create_date", now)
    # df.insert(len(df.columns), "update_date", now)

    # df.index = df.index + 1
    # # csv 파일 생성
    # df.to_csv(
    #     f"./data/performance_{category}_{year}.csv",
    #     mode="w",
    #     encoding="utf-8-sig",
    #     header=True,
    #     index=False,
    # )

    # performance_set.clear()

    #######################################################################

    # # sql 저장
    # df.to_sql(
    #     name="performance",
    #     con=db_connection,
    #     if_exists="append",
    #     chunksize=1000,
    #     index=False,
    #     method="multi",
    # )

    # # mysql output 불러오기
    # query = "SELECT * FROM performance"

    # df = pd.read_sql_query(query, conn)
    # df.to_csv(r"./data/mysql_output_performance.csv", index=False)


#### 시즌 테이블 생성 함수 ####
def makeSeason():
    df = pd.DataFrame(season_list, columns=season_column)
    df.index = df.index + 1
    df.to_csv(
        f"./data/season.csv", mode="w", encoding="utf-8-sig", header=True, index=False
    )
    # sql 저장
    df.to_sql(
        name="season",
        con=db_connection,
        if_exists="append",
        chunksize=1000,
        index=False,
        method=insert_on_duplicate,
    )


#### 배우 테이블 생성 함수 ####
def makeActor():
    input_data = list(actor_list.values())
    df = pd.DataFrame(input_data, columns=actor_column)
    df.index = df.index + 1
    df.to_csv(
        f"./data/actor.csv", mode="w", encoding="utf-8-sig", header=True, index=False
    )
    df.to_sql(
        name="actor",
        con=db_connection,
        if_exists="append",
        chunksize=1000,
        index=False,
        method="multi",
    )


#### 캐스팅 테이블 생성 함수 ####
def makeCasting():
    df = pd.DataFrame(casting_list, columns=casting_column)
    df.index = df.index + 1
    df.to_csv(
        f"./data/casting.csv", mode="w", encoding="utf-8-sig", header=True, index=False
    )
    df.to_sql(
        name="casting",
        con=db_connection,
        if_exists="append",
        chunksize=1000,
        index=False,
        method="multi",
    )


#### 공연 상세 정보 추출 함수 ####
def showDetail(season_id, category):
    # 공연 페이지로 이동
    driver.get(show_url.format(season_id))
    driver.implicitly_wait(2)
    html = driver.page_source
    soup = BeautifulSoup(html, "html.parser")

    # performance db csv 파일 읽기
    csv = pd.read_csv("mysql_output_performance.csv")

    # 공연 이름, 공연 종류 + 인터파크 id, playDB id, 시즌 사진, 공연 시작일자, 공연 종료일자, 작품 설명, 공연 장소, 관람 시간, 관람 등급, 세부 장르, 러닝 여부
    performance_name = None  #
    performance_type = category  #

    goods_code = None  #
    playdb_id = season_id  #
    season_image = None  #
    start_date = None  #
    end_date = None  #
    description = ""  #
    location = None  #
    running_time = None  #
    performance_age = None  #
    detail_type = None  #
    proceed_flag = None  #

    # 출연진
    actor_role = None

    # 제목
    performance_name = driver.find_element_by_xpath(
        '//*[@id="wrap"]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td/span[1]'
    ).text
    # 이미지
    season_image = driver.find_element_by_css_selector(
        "#wrap > div.pddetail > h2 > img"
    ).get_attribute("src")
    # 세부 정보 리스트
    detail_list = driver.find_element_by_css_selector(
        "#wrap > div.pddetail > div.pddetail_info > div.detaillist > table > tbody"
    ).find_elements_by_tag_name("tr")

    # 세부 정보 추출
    for e in detail_list:
        temp = e.find_elements_by_tag_name("td")
        column = temp[0].find_element_by_tag_name("img").get_attribute("alt")
        # 어린이/가족 제외
        if column == "세부장르":
            detail_genre = temp[1].find_elements_by_tag_name("a")[1].text
            # if "어린이/가족" == detail_genre :
            #   return
            # else :
            detail_type = detail_genre
        elif column == "출연":
            continue
        elif column == "일시":
            detail_date = temp[1].text.split("~")
            start_date = detail_date[0].strip()
            end_date = detail_date[1].strip()

            start_date_detail = list(map(int, start_date.split("/")))
            end_date_detail = list(map(int, end_date.split("/")))

            start_date_cmp = datetime(
                start_date_detail[0], start_date_detail[1], start_date_detail[2]
            )
            end_date_cmp = datetime(
                end_date_detail[0], end_date_detail[1], end_date_detail[2]
            )

            # 진행 예정
            if start_date_cmp > now:
                proceed_flag = 2
            # 진행 중
            elif start_date_cmp <= now and end_date_cmp > now:
                proceed_flag = 1
            # 완료
            elif end_date_cmp < now:
                proceed_flag = 0

        elif column == "장소":
            location = temp[1].find_element_by_tag_name("a").text
        elif column == "관람등급":
            performance_age = temp[1].text
        elif column == "관람시간":
            running_time = temp[1].text

    # 인터파크 ID 추출
    # goods_code = soup.select_one(".detaillist > p > a")
    # if goods_code != None :
    #   goods_code = goods_code['href'][-8:]
    goods_code = soup.select_one(".detail_contentsbox4 > .title > a")
    if goods_code != None:
        goods_code = goods_code["href"].split("?")[1][10:18]

    # 배우 정보 추출
    boxes = soup.select(
        ".detail_contentsbox > table > tbody > tr > td > table > tbody > tr"
    )

    for item in boxes:
        actor_role = item.select_one("td").text
        actor_image = None
        actor_id = None
        actor_name = None

        actorsBox = item.select("td > a")
        for index, i in enumerate(actorsBox):
            if index % 2 == 0:
                actor_image = i.select_one("img")["src"]
            else:
                actor_id = i["href"].split("=")[1]
                actor_name = i.get_text()

            # 배우 정보 추가
            if index % 2 == 1:
                if id not in actor_list:
                    actor_list[actor_id] = [
                        None,
                        actor_name,
                        int(actor_id),
                        actor_image,
                        now,
                    ]  # actor_id 안에 넣어서 밸류값으로 db에 넣으면 괜춘??
                    # 캐스팅 정보 추가
                casting_list.append([None, season_id, actor_id, actor_role, now])

    driver.find_element_by_xpath('//*[@id="Tab2"]').click()
    driver.switch_to.frame("iFrmContent")

    try:
        description = driver.find_element_by_class_name("news").text
    except NoSuchElementException as e:
        description = None

    # 나머지 정보 csv파일에서 추출
    # 공연 타입, 이름에 해당하는 performance_id 검색
    performance_id = csv.loc[
        (csv["performance_name"] == performance_name)
        & (csv["performance_type"] == int(performance_type)),
        "id",
    ].iat[0]

    season_list.append(
        [
            None,
            performance_id,
            goods_code,
            playdb_id,
            season_image,
            start_date,
            end_date,
            description,
            location,
            running_time,
            performance_age,
            detail_type,
            proceed_flag,
            now,
        ]
    )

    print("아이디 : " + season_id, " 공연 상세 완료")


##### 연도, 카테고리별 공연 목록 크롤링 함수 #####
def showList(page, category, sub_category, year):
    # 페이지 이동
    driver.get(list_url.format(page, category, sub_category, year))
    driver.implicitly_wait(0.5)

    # 공연 이름, playdb ID 추출하기
    lists = driver.find_elements_by_css_selector(
        "#contents > div.container1 > table > tbody > tr:nth-child(11) > td > table > tbody > tr > td"
    )

    for item in lists:
        # 어린이 카테고리 필터링
        # if "어린이" not in item.text:

        if not item.find_elements_by_tag_name("a"):
            continue
        # 지역 필터링
        id = item.find_element_by_tag_name("a").get_attribute("onclick")
        if id is None:
            continue
        title = item.find_element_by_tag_name("a").text
        if "-" in title:
            continue

        show_list.append(id.split("'")[1])
        performance_set.add(title)


# 중복 ID 업데이트 함수
def insert_on_duplicate(table, conn, keys, data_iter):
    insert_stmt = insert(table.table).values(list(data_iter))
    on_duplicate_key_stmt = insert_stmt.on_duplicate_key_update(insert_stmt.inserted)
    conn.execute(on_duplicate_key_stmt)


# ---------------------------------Main---------------------------------------

options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])

# 크롬 드라이버 (에러 나면 절대 경로로 바꾸기)
driver = webdriver.Chrome(executable_path="./chromedriver", options=options)
# url
list_url = "http://www.playdb.co.kr/playdb/playdblist.asp?Page={}&sReqMainCategory={}&sReqSubCategory={}&sReqDistrict=&sReqTab=2&sPlayType=&sStartYear={}&sSelectType=1"
show_url = "http://www.playdb.co.kr/playdb/playdbDetail.asp?sReqPlayno={}"

season_column = [
    "id",
    "performance_id",
    "interpark_id",
    "playdb_id",
    "season_image",
    "start_date",
    "end_date",
    "description",
    "location",
    "runningtime",
    "performance_age",
    "detail_type",
    "proceed_flag",
    "create_date",
]
casting_column = ["id", "season_id", "actor_id", "role", "create_date"]
actor_column = ["id", "actor_name", "playdb_id", "actor_image", "create_date"]
sub_category_01 = ["001001", "001002", "001003", "001005", "001006"]
sub_category_02 = ["002001"]

# db_connection_str = (
#     "mysql+pymysql://root:" + "ssafy" + "@127.0.0.1:3306/showing?charset=utf8"
# )
# db_connection = create_engine(db_connection_str)
# conn = db_connection.connect()

# 공연 id 목록 list
show_list = []
# 공연 이름 set
performance_set = set()
# 공연 정보 목록 list
season_list = []
# 배우 정보 목록
actor_list = {}
# 캐스팅 정보 목록
casting_list = []

# 현재 시간
now = datetime.now()
start_time = time.time()

# startCrawling("2022", "000001")
for i in range(2015, 2023):
    startCrawling(str(i), "000002")

print("수행시간 : ", time.time() - start_time)
driver.quit()
