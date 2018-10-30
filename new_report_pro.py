# coding:utf-8
# linux系统跑Python脚本的crontab任务，一定要用绝对路径
import re
import os
import random
import pymysql
import requests
import datetime
from bs4 import BeautifulSoup
from io import BytesIO
from PIL import Image, ImageDraw, ImageFont


def image_upload(img_name, img_url):
    """获取到的图片url上传到线上图片服务器，如：https://image.toobei.com/bb5c4a254d93fb49ca0900a17478f729"""
    upload_url = "https://image.toobei.com/upload"  # 预发布的图片服务上传地址
    req = requests.get(img_url)
    im = Image.open(BytesIO(req.content))
    ims = im.convert('RGB')
    # ims.save("/data/liecai_daily_report/new_report/images/%s.jpg" % img_name)
    ims.save("./images/%s.jpg" % img_name)
    # file = {'file': ('%s.jpg' % img_name, open("/data/liecai_daily_report/new_report/images/%s.jpg" % img_name, "rb"), 'image/jpeg')}
    file = {'file': ('%s.jpg' % img_name, open("./images/%s.jpg" % img_name, "rb"), 'image/jpeg')}
    resp = requests.post(upload_url, files=file)
    md5_text = re.findall(r'MD5:(.*?),</body>', resp.text)
    md5_text_str = ''.join(md5_text)
    return md5_text_str


def image_manage():
    """每日早报图片处理，自动加上当日时间"""
    year = datetime.datetime.now().year
    month = datetime.datetime.now().month
    day = datetime.datetime.now().day
    date_nowtime = datetime.datetime.now().strftime("%Y.%m.%d")
    # im = Image.open('/data/liecai_daily_report/new_report/daily_images/image.jpg')
    im = Image.open('./images/image.jpg')   # 选定图片,服务器上一定要绝对路径，/data/liecai_daily_report/new_report/daily_images/image.jpg
    draw = ImageDraw.Draw(im)  # 编辑图片
    # newfont = ImageFont.truetype('/data/liecai_daily_report/new_report/msyh.ttf', 28)  # 选定字体与大小
    newfont = ImageFont.truetype('./msyh.ttf', 28)  # 选定字体与大小
    draw.text((239, 172), u'%s年%s月%s日' % (year, month, day), (255, 255, 255), font=newfont)  # 往图片中写入每天的日期
    # im.save('/data/liecai_daily_report/new_report/daily_images/image%s.jpg' % date_nowtime)  # 保存新图片，赋予当时的时间戳
    im.save('./images/image%s.jpg' % date_nowtime)  # 保存新图片，赋予当时的时间戳
    # url = "https://preimage.toobei.com/upload"   # 预发布的图片服务上传地址
    url = "https://image.toobei.com/upload"  # 线上服务器地址
    # file = {'file': ('image%s.jpg' % date_nowtime, open('/data/liecai_daily_report/new_report/daily_images/image%s.jpg' % date_nowtime, 'rb'), 'image/jpeg')}
    file = {'file': ('image%s.jpg' % date_nowtime,open('./images/image%s.jpg' % date_nowtime, 'rb'), 'image/jpeg')}
    resp = requests.post(url, files=file)
    md5_text = re.findall(r'MD5:(.*?),</body>', resp.text)
    md5_text_str = ''.join(md5_text)
    return md5_text_str


class ReportDeco(object):
    def __init__(self):
        Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"
        AcceptEncoding = "gzip, deflate"
        AcceptLanguage = "zh-CN,zh;q=0.9"
        Connection = "keep-alive"
        Cookie = "Sound=on; AutoRefresh=on; lastTime=1517533340000; Hm_lvt_12e1190b72b553cf5650d32e6e87ba96=1523246427,1523353569,1523410475,1523413738; aliyungf_tc=AQAAAEoBekRWzwYAotsDt3IEr0z62V3k; acw_tc=AQAAAH+Pb08g3QYAotsDt1rBMQBYVP/h; Hm_lpvt_12e1190b72b553cf5650d32e6e87ba96=1523415685; ci_sessionm=ErIKwAsIMUFQIw7kiialMYT%2FzI8fMFPvIjU0cjilibhCS8vqj0qhstETb2HnAmtr9RIYpFSQmRbObRxFDHkvtiDG9FluVQYw8wjmypszkZTq9Ku%2F9Ww9wWcehDYpjuUOf4DAoRXUnqJvYWYKaa9EXzn4FuMkjhCiwvohODqJOucasv7oaqzD8OLOp8lcdJBBxjt4j3E6F%2BEIYWTrPGSCwJ3FcCdYOBujzzP1goLRvqOS1d6S4FTn15diEAyzNFOZd3spzW6mLoMMRkl8lZwDhyeBvZHRbziQygvOY5fdJ7riZM26%2BqPgNn6JTcyoSv0ywE8tMhnQpJ8gGqWsSoWL9abygnvy1AbTtYvkxRxdO1cOGNb4h49aEYE4bjrojVA6QFSRJ9G%2FsUNPc1b%2FqpwVzndgpMBuhdNAOAMxnp3%2Bg7BzMy70LGPZdkOh%2Brw1zFwjfYWC5Kd2OMMwTttR1wda%2Fbp9AUx6%2FK0WQ8w6e88rvYKjLW85jgWbgUiyDcAuIe%2BciApBJVdA%2FeWhwdNospkcROi9UAUgtETOfPKDPgT2tl0DmoqVtvolCNkbgCMLPXzrTyjs6oe32WczD5SoX5ieF4JVGyfoqtgqhWEQNtFgaqDktgmBIaT9%2FU5cJk%2FKZafG75lGj8hdIHdurC1lfn08Q67LaoKTETpEfuGqHaUoydHq0okB9hBREGNlIJH22%2Fbhh6C91X4Hn2MSvCq4d%2BAADlT4MncZukFzG%2BUWrKg8fXeaDPGpApusOH%2BeaxcsZi6VPZeAwPF2aIu5x9c7gxlzdVolL5jphPgDbQ3hUMpdQCXdLwwMJaR7olUVHokrdlC1LUPHY%2B%2Bk%2FvADOU5uGmf9TxiuEPSDk%2Bkh0FZTx9R1E6pHG7px5K1OFeP7qS8kQfma1da2a2b1d8a7a9c0c2ac2271dc08ca9ab8ec7193"
        Host = "www.lanjinger.com"
        UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36"
        self.Headers = {"Accept": Accept, "Accept-Encoding": AcceptEncoding, "Accept-Language": AcceptLanguage,
                        "Connection": Connection, "Cookie": Cookie, "Host": Host, "User-Agent": UserAgent}
        self.Url = "http://www.lanjinger.com/news/10/"  # 主url
        self.per_url = "http://www.lanjinger.com/news/detail?id="  # 信息列表，包含晚报url(与后续取出的id值组合成完整url)
        self.html_content1 = """<style>.article_content_wrap{padding: .2rem;font-size: .18rem;color:black;text-align:justify; line-height:1.84; text-indent:2em;} 
                    .article_content_wrap .paraTitle {color: red;text-indent: 0;padding: .2rem 0;font-size: .2rem!important;}
                    .article_content_wrap img{padding: .2rem 0;margin-left: -2em;}</style> """
        self.html_content2 = """<br><div>
                    <p style="font-weight: bold;color: #3a9dff;">附：信用宝攻略：(投1万一月标，收益13.26%)</p>
                    <p style="color: #000;font-weight: bold;">1、产品:步步高</p>
                    <p style="color: #000;font-weight: bold;">2、年化收益8%-12%,每多投一个月收益增加1%，最高12%</p>
                    <p style="color: #000;font-weight: bold;">3、解冻0.2%投资金额的红包，第二次投资时可以使用，相当于加息2.4%</p>
                    <p style="color: #000;font-weight: bold;">4、理财师自投还可以享受佣金0.7%</p>
                    <p style="color: #000;font-weight: bold;">5、猎财新手红包18元，相当于加息2.16%</p></div>"""

    def time_url_nid(self):
        """根据主url，取出每个内容网址中的时间,对应的banner图片url,nid,具体url， 如：['2018-04-17',
        'https://img.lanjinger.com/lanjingapp/default/20180417/155910_5ad5a94edcbbd.jpg', '86731',
        'http://www.lanjinger.com/news/detail?id=86731','今天大新闻', '分享新闻标题'] """
        data_nid_list = []
        req = requests.get(self.Url, self.Headers)
        soup = BeautifulSoup(req.content, "lxml")
        for i in soup.find_all('div', class_="telegraph_item"):
            s_time = datetime.datetime.fromtimestamp(int(i["data-ctime"])).strftime("%Y-%m-%d")
            pic_url = i.find('img', class_="img_item")["data-src"]
            brief_contents = i.find('div', class_="news_brief").text
            share_title = i.find('div', class_="news_title").text
            data_nid_list.append(
                [s_time, pic_url, i["data-nid"], self.per_url + i["data-nid"], brief_contents, share_title])
        return data_nid_list

    def daily_report(self, time_list):
        """以当天时间为准，取前一天的互金晚报作为今天的早报的url,
        如：http://www.lanjinger.com/news/detail?id=86681"""
        yesterday_times = (datetime.datetime.now() - datetime.timedelta(days=1)).strftime(
            "%Y-%m-%d")  # 昨天日期年月日，如：2018-04-12
        yesterday_urls = []  # 前一天的url列表
        report_daily_url = ''
        for i in time_list:
            if yesterday_times in i:
                yesterday_urls.append(i[3])
        for j in sorted(yesterday_urls):
            req = requests.get(j, headers=self.Headers)
            soup = BeautifulSoup(req.content, "lxml")
            for k in soup.find_all("title"):
                if "蓝鲸财经-互金晚报" in k.text:
                    report_daily_url = j
        return report_daily_url

    def info_url_content(self, url):
        """只取出传进来的url列表中的内容，以整个div为返回值，可传入到数据库中
        如：<div class="article_content_wrap"> <p> contents</p> </div>,返回文章标题内容"""
        req = requests.get(url, headers=self.Headers)
        soup = BeautifulSoup(req.content, "lxml")
        main_contents = ''
        title_content = ''
        for i in soup.find_all('div', class_="content_left"):
            title_content = i.find('div', class_="article_title").text
            main_contents = i.find('div', class_="article_content_wrap")
        return main_contents, title_content

    def com_report_list(self):
        """所有分散的结果值整合成一个列表对应,此为晚报列表所有内容列表"""
        time_div = self.time_url_nid()
        report_url = self.daily_report(time_div)
        report_list = ''  # 前一天晚报所有的字段列表
        # old_img_list = os.listdir('/data/liecai_daily_report/new_report/images')
        old_img_list = os.listdir('./images')
        old_nid_list = []  # 现有的图片ID列表
        if old_img_list:
            for k in old_img_list:
                old_nid_list.append(k.replace('.jpg', ''))  # 判断已经爬到的nid，放入列表中，防止后续继续爬取
        for i in time_div:
            if report_url in i:
                if i[2] not in old_nid_list:
                    img_md5 = image_upload(i[2], i[1])
                    report_content = self.info_url_content(i[3])
                    i.append(img_md5)
                    i.append(report_content[0])
                    i.append(report_content[1])
                    report_list = i
        return report_list  # 前一天晚报内容列表

    def com_all_list(self):
        """所有分散的结果值整合成一个列表对应,此为剔除晚报列表之后所有内容列表"""
        time_div = self.time_url_nid()
        report_list = self.com_report_list()
        remove_report_list = []
        last_message_list = []  # 最终不含前一天每日晚报的所有字段列表
        # old_img_list = os.listdir('/data/liecai_daily_report/new_report/images')
        old_img_list = os.listdir('./images')
        old_nid_list = []  # 现有的图片ID列表
        if old_img_list:
            for k in old_img_list:
                old_nid_list.append(k.replace('.jpg', ''))  # 判断已经爬到的nid，放入列表中，防止后续继续爬取
        if report_list:
            for i in time_div:
                if report_list[3] not in i:
                    remove_report_list.append(i)
        else:
            remove_report_list = time_div
        for j in remove_report_list:
            if j[2] not in old_nid_list:
                last_img_md5 = image_upload(j[2], j[1])
                last_content = self.info_url_content(j[3])
                j.append(last_img_md5)
                j.append(last_content[0])
                j.append(last_content[1])
                last_message_list.append(j)
        return last_message_list

    def post_sql_contents(self, md5_url, share_title, share_co, div_content):
        """根据获取到的数据写入数据库tsm_news"""
        datetime_now = str(datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))  # 获取发送的当前时间
        img_upload_url = "https://image.toobei.com/"
        div_contents = '''%s''' % div_content
        div_contents2 = div_contents.replace("\'", '\"')
        random_num = random.randint(800, 1000)
        # db = pymysql.connect(host='120.76.97.142', user='root', passwd='linghui66123', db='supermarket', port=3306,
        #                      charset='utf8')  # 预发布环境的mysql
        db = pymysql.connect(host="rm-wz9h72p13h38q4qu7o.mysql.rds.aliyuncs.com", user="lhlc",
                             passwd="lhkj#201609!", db="supermarket", port=3306, charset="utf8")   # 注意区别内外网地址，外网地址
        # db = pymysql.connect(host="rm-wz9h72p13h38q4qu7.mysql.rds.aliyuncs.com", user="lhlc",
        #                      passwd="lhkj#201609!", db="supermarket", port=3306, charset="utf8")   # 注意区别内外网地址，内网地址
        cursor = db.cursor()  # 获取数据操作游标，针对表：tsm_news
        cursor.execute('''insert into tsm_news(app_type, name, type_code, type_name, img, title, summary, content, status, creator, crt_time, valid_begin, modifiy_time, is_stick, share_icon, reading_amount) 
                      values (1,NULL ,5,'看一看',''' + '\'%s%s\'' % (img_upload_url, md5_url) + ',' + '\'%s\'' % share_title + ',' + '\'%s\'' % share_co + ',' + '\'%s%s\'' % (
                       self.html_content1, div_contents2)
                       + ''',0,'猎财大师',''' + '\'%s\'' % datetime_now + ',' + '\'%s\'' % datetime_now + ',' + '\'%s\'' % datetime_now + ''',0,''' + '\'%s\'' % md5_url + ',' + '\'%s\'' % random_num + ''')''')
        db.commit()
        db.close()

    def post_report_sql_contents(self, md5_url, share_co, div_content):
        """根据获取到的数据写入数据库tsm_classroom"""
        year = datetime.datetime.now().year
        month = datetime.datetime.now().month
        day = datetime.datetime.now().day
        datetime_now = str(datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))  # 获取发送的当前时间
        div_contents = '''%s''' % div_content
        div_contents2 = div_contents.replace("\'", '\"')
        img_upload_url = "https://image.toobei.com/"
        # db = pymysql.connect(host='120.76.97.142', user='root', passwd='linghui66123', db='supermarket', port=3306,
        #                      charset='utf8')  # 预发布环境的mysql
        db = pymysql.connect(host="rm-wz9h72p13h38q4qu7o.mysql.rds.aliyuncs.com", user="lhlc",
                             passwd="lhkj#201609!", db="supermarket", port=3306, charset="utf8")   # 注意区别内外网地址，外网地址
        # db = pymysql.connect(host="rm-wz9h72p13h38q4qu7.mysql.rds.aliyuncs.com", user="lhlc",
        #                      passwd="lhkj#201609!", db="supermarket", port=3306, charset="utf8")   # 注意区别内外网地址，内网地址
        cursor = db.cursor()  # 获取数据操作游标，针对表：tsm_classroom
        cursor.execute('''insert into tsm_classroom(app_type, img, title, summary, content, status, creator, show_inx, valid_begin, valid_end, label, share_title, share_desc, share_icon) 
                      values(1, ''' + '\'%s%s\'' % (img_upload_url, md5_url) + ''','猎财头条早知道',''' + '\'【猎财头条】%s\'' % share_co + ',' + '\'%s%s\'' % (self.html_content1, div_contents2)
                       + ''',0,'猎财大师',1,''' + '\'%s\'' % datetime_now + ',' + '\'%s\'' % datetime_now + ''','今日财经早知道',''' + '\'猎财头条早知道%s年%s月%s日\'' % (
                       year, month, day) + ',' + '\'【猎财头条】%s\'' % share_co + ''','https://image.toobei.com/56a1917404cfc0b80ed9e16b2ac70f75')''')  # 插入获取前一天晚报内容
        db.commit()
        db.close()


if __name__ == "__main__":
    """最终组合成一列表,如：['time','img_url', 'nid','report_url','share_content','share_title','pic_md5','div_content',
    'new_title'] """
    date_now_time = datetime.datetime.now().strftime("%Y.%m.%d")
    ins_report = ReportDeco()
    # print(ins_report.time_url_nid())
    print(ins_report.daily_report(ins_report.time_url_nid()))
    # news_report_list = ins_report.com_report_list()  # 把抓取到的列表存入一个列表，防止重复调用
    # f = news_report_list
    # print(news_report_list)
    # if f:
    #     md5_str = image_manage()
    #     ins_report.post_report_sql_contents(md5_str, f[4], f[7])  # 写入每日早报
    #     with open("/data/liecai_daily_report/detection", "w") as f:
    #         f.write(date_now_time)
    #         f.close()
    # else:
    #     os.system('echo "日报列表暂时没有更新,`date +"%Y.%m.%d %H:%M:%S"`" >> /data/liecai_daily_report/new_report/news_list.txt')
    # news_all_list = ins_report.com_all_list()  # 把抓取到的列表存入一个列表，防止重复调用
    # print(news_all_list)
    # if news_all_list:
    #     for j in news_all_list:
    #         if "金小鲸(id：lanjinghj)" in j[7] or "金小鲸(id:lanjinghj)" in j[7]:
    #             rep_div_content = j[7].replace("金小鲸(id：lanjinghj)", "猎财大师(liecaidashi)")
    #             ins_report.post_sql_contents(j[6], j[8], j[4], rep_div_content)  # 新闻列表写入
    #         else:
    #             ins_report.post_sql_contents(j[6], j[8], j[4], j[7])  # 新闻列表写入
    # else:
    #     os.system('echo "新闻列表暂时没有更新,`date +"%Y.%m.%d %H:%M:%S"`" >> /data/liecai_daily_report/new_report/news_list.txt')
