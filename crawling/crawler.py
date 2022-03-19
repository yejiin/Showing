import csv
import selenium
import pandas as pd
from selenium import webdriver
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from datetime import datetime

########## 시작 함수 ##########
def startCrawling(year, category):
  print("--------"+year+"년 "+category+" 크롤링 시작 --------")  
  # 1페이지로 이동
  driver.get(list_url.format(1,category,year))
  
  # 총 페이지 수 추출
  pageNum = driver.find_element_by_xpath('/html/body/div[1]/div[2]/div[2]/table/tbody/tr[11]/td/table/tbody/tr[35]/td').text
  pageNum = pageNum.split('/')[1]
  pageNum = pageNum[0:len(pageNum)-1]
  
  # 페이지 별 공연 ID 크롤링
  for i in range(1,int(pageNum)+1):
    showList(i,category,year)
  
  # for i in show_list:
  #   showDetail(i, category)


#### 공연 테이블 생성 함수 ####
def makePerformance(category):
  
  df = pd.DataFrame(perfomance_set, columns=['performance_name'])
  df.insert(0,'id',"")
  df.insert(1,'last_season_id',"")
  df.insert(len(df.columns),'performance_image',"")
  df.insert(len(df.columns),'performance_type',category)
  df.insert(len(df.columns),'create_date',"")
  df.insert(len(df.columns),'update_date',"")

  df.index = df.index +1
  df.to_csv(f'temp.csv',mode='w',encoding='utf-8-sig',header=True,index=True)


#### 시즌 테이블 생성 함수 ####
def makeSeason():
  df2 = pd.DataFrame(season_list, columns = season_column)
  df2.index = df2.index +1
  df2.to_csv(f'season_temp.csv',mode='w',encoding='utf-8-sig',header=True,index=True)


#### 공연 상세 정보 추출 함수 ####
def showDetail(showId, category):
  # 공연 페이지로 이동
  driver.get(show_url.format(showId))
  driver.implicitly_wait(5)
  html = driver.page_source
  soup = BeautifulSoup(html,'html.parser')

  # 공연 이름, 공연 종류 + 인터파크 id, playDB id, 시즌 사진, 공연 시작일자, 공연 종료일자, 작품 설명, 공연 장소, 관람 시간, 관람 등급, 세부 장르, 러닝 여부
  perfomance_name = None #
  perfomance_type = category #

  goods_code = None #
  playdb_id = showId #
  season_image = None #
  start_date = None #
  end_date = None #
  description = "" #
  location = None # 
  running_time = None #
  perfomance_age = None #
  detail_type = None #
  proceed_flag = None #
  
  # 출연진
  actor_images = []
  actor_names = []
  actor_ids = []
  actors = []
  
  # 제목
  perfomance_name = driver.find_element_by_xpath('//*[@id="wrap"]/div[3]/div[1]/div[1]/table/tbody/tr[1]/td/span[1]').text
  # 이미지
  season_image = driver.find_element_by_css_selector('#wrap > div.pddetail > h2 > img').get_attribute("src")
  # 세부 정보 리스트
  detail_list = driver.find_element_by_css_selector('#wrap > div.pddetail > div.pddetail_info > div.detaillist > table > tbody').find_elements_by_tag_name("tr")

  # 세부 정보 추출
  for e in detail_list : 
    temp = e.find_elements_by_tag_name("td")
    column = temp[0].find_element_by_tag_name("img").get_attribute("alt")
    # 어린이/가족 제외
    if column == "세부장르" :
      detail_genre = temp[1].find_elements_by_tag_name("a")[1].text
      if "어린이/가족" == detail_genre :
        return
      else :
        detail_type = detail_genre
    elif column == "출연" : 
      continue
    elif column == "일시" : 
      detail_date = temp[1].text.split("~")
      start_date = detail_date[0].strip()
      end_date = detail_date[1].strip()

      start_date_detail = list(map(int, start_date.split("/")))
      end_date_detail = list(map(int, end_date.split("/")))

      start_date_cmp = datetime(start_date_detail[0],start_date_detail[1],start_date_detail[2])
      end_date_cmp = datetime(end_date_detail[0],end_date_detail[1],end_date_detail[2])

      # 진행 예정
      if start_date_cmp > now :
        proceed_flag = 2
      # 진행 중
      elif start_date_cmp <= now and end_date_cmp > now :
        proceed_flag = 1
      # 완료
      elif end_date_cmp < now :
        proceed_flag = 0

    elif column == "장소":
      location = temp[1].find_element_by_tag_name("a").text
    elif column == "관람등급" :
      perfomance_age = temp[1].text
    elif column == "관람시간" :
      running_time = temp[1].text

  # 인터파크 ID 추출
  goods_code = soup.select_one(".detail_contentsbox4 > .title > a")
  if goods_code != None :
    goods_code = goods_code['href'].split("?")[1][10:18]

  # 배우 정보 추출
  boxes = soup.select(".detail_contentsbox > table > tbody > tr > td > table > tbody > tr > td > a")

  for index, item in enumerate(boxes) :
    if index % 2 == 0 :
      actor_images.append(item.select_one('img')['src'])
    else :
      actor_ids.append(item['href'].split('=')[1])
      actor_names.append(item.get_text())

  actors = list(zip(actor_ids,actor_images,actor_names))

  driver.find_element_by_xpath('//*[@id="Tab2"]').click()
  driver.switch_to.frame("iFrmContent")

  contents = driver.find_elements_by_class_name('news')
  
  # 작품 설명 추출
  if contents is not None:
    description = contents[0].text
  
  season_list.append([perfomance_name, perfomance_type, goods_code, playdb_id, season_image, start_date, end_date, description, location, running_time, perfomance_age, detail_type, proceed_flag])

  # 배우 csv 파일 생성
  # df = pd.DataFrame(actors, columns=['id','image','name'])
  # df.insert(0,'show_id',showId)
  # df.index = df.index +1
  # df.to_csv(f'temp.csv',mode='w',encoding='utf-8-sig',header=True,index=True)
  

##### 연도, 카테고리별 공연 목록 크롤링 함수 #####
def showList(page,category,year): 
  # 페이지 이동
  driver.get(list_url.format(page,category,year))

  # 공연 이름, playdb ID 추출하기 
  lists = driver.find_elements_by_css_selector('#contents > div.container1 > table > tbody > tr:nth-child(11) > td > table > tbody > tr > td')
  
  for item in lists:
    # 어린이 카테고리 필터링
    if "어린이" not in item.text:
      
      if not item.find_elements_by_tag_name('a'):
        continue
      # 지역 필터링
      id = item.find_element_by_tag_name('a').get_attribute('onclick')
      if id is None:
        continue
      title = item.find_element_by_tag_name('a').text
      if "-" in title:
        continue

      show_list.append(id.split('\'')[1])
      perfomance_set.add(title)
  
#---------------------------------Main---------------------------------------

# 크롬 드라이버 (에러 나면 절대 경로로 바꾸기)
driver = webdriver.Chrome('./chromedriver')
# url
list_url = "http://www.playdb.co.kr/playdb/playdblist.asp?Page={}&sReqMainCategory={}&sReqSubCategory=&sReqDistrict=&sReqTab=2&sPlayType=&sStartYear={}&sSelectType=1"
show_url = "http://www.playdb.co.kr/playdb/playdbDetail.asp?sReqPlayno={}"

season_column = ["perfomance_name", "perfomance_type", "interpark_id", "playdb_id", "season_image", "start_date", "end_date", "description", "location", "runningtime", "perfomance_age", "detail_type", "proceed_flag"]

# 공연 id 목록 list
show_list = []
# 공연 이름 set
perfomance_set = set()
# 공연 정보 목록 list
season_list = []

# 현재 시간
now = datetime.now()
nowyear = int(now.year)
nowmonth = int(now.month)
nowday = int(now.day)

# startCrawling('2022', '000001')
# showDetail(146154,'000001')
# makeSeason()
#showList('1','000001','2022')

# 다음 웹페이지가 넘어올때까지 1초 기다림
driver.implicitly_wait(1)

driver.quit()