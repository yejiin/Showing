import selenium
from selenium import webdriver
from bs4 import BeautifulSoup

def startCrawling(year, category):  
  # 1페이지로 이동
  driver.get(base_url.format(1,category,year))
  
  # 총 페이지 수 추출
  pageNum = driver.find_element_by_xpath('/html/body/div[1]/div[2]/div[2]/table/tbody/tr[11]/td/table/tbody/tr[35]/td').text
  pageNum = pageNum.split('/')[1]
  pageNum = pageNum[0:len(pageNum)-1]
  
  # 페이지 별 공연 번호 크롤링
  for i in range(1,int(pageNum)+1):
    showList(i,category,year)
  
  for i in show_list:
    showDetail(i)
  

# 각 공연 별 상세 데이터 크롤링 함수
def showDetail(showNum):
  driver.get(show_url.format(showNum))

  return 0

# 연도, 카테고리별 공연 목록 크롤링 함수
def showList(page,category,year): 
  # 페이지 이동
  driver.get(base_url.format(page,category,year))

  # 공연 이름, playdb id 추출하기
  lists = driver.find_element_by_xpath('//*[@id="contents"]/div[2]/table/tbody/tr[11]/td/table/tbody').find_elements_by_tag_name('a')
  
  # 지역 필터링
  for content in lists :
    temp = content.get_attribute('onclick')
    if temp is None:
      continue
    title = content.text
    if '-' in title:
      continue
    id = temp.split('\'')[1]
  
    show_list.append(id)
    
#------------------------------------------------------------------------

# 크롬 드라이버 (에러 나면 절대 경로로 바꾸기)
driver = webdriver.Chrome('./chromedriver')
# url
base_url = "http://www.playdb.co.kr/playdb/playdblist.asp?Page={}&sReqMainCategory={}&sReqSubCategory=&sReqDistrict=&sReqTab=2&sPlayType=&sStartYear={}&sSelectType=1"
show_url = "http://www.playdb.co.kr/playdb/playdbDetail.asp?sReqPlayno={}"
# 공연 목록 list
show_list = []

startCrawling('2022', '000001')

# 다음 웹페이지가 넘어올때까지 1초 기다림
driver.implicitly_wait(1)

driver.quit()