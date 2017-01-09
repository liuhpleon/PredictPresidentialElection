'''
Created on Dec 2, 2016

@author: Haopeng Liu
'''

import tweepy
import time
from tweepy import OAuthHandler
'''
this is the init of tweepy.API(), here I just register 3 application to increase the downloading speed.
'''
def getapi():
    ck1 = '0oHGrxXtxy2FOfogQxGYN1E6o'
    cs1 = 'UVmp7ivZVTsj8xM1nAuq5b1Il5BYnGqwZsCs73mm3XhfRp9zjh'
    ak1 = '784227709496942592-kPeQVVHkrJDVddLuCTQ6ZghNr3TTUxQ'
    aks1 = 'vqKoofioADWxTt5ZfDmOvOomMoTtu5JouwApSSmwBHnbm'
    auth1 = OAuthHandler(ck1, cs1)
    auth1.set_access_token(ak1, aks1)
    api1 = tweepy.API(auth1)
    ck2 = 'bgGC9IcHwmDedJp2bEadyvBp8'
    cs2 = 'XAGSr1ocAoNsylSwSkalYETvm0W1L9P48eqSdHTkTekXljfbKA'
    ak2 = '784227709496942592-vlRK4TCbqnGr8Ld8loSnnbbFMry7FrK'
    aks2 = 'rZFQXkBqPOdrc2iOt3LmPuJPdZRzb5whiL3gphKvFB4mc'
    auth2 = OAuthHandler(ck2, cs2)
    auth2.set_access_token(ak2, aks2)
    api2 = tweepy.API(auth2)
    ck3 = 'turdwl5vKCiXnaU2yOqeFlaVP'
    cs3 = 'fR5o9ZsWZ6sBgfdaGsIt1XG3TwfnvOgqKsJViVfWXbpnM77Z8g'
    ak3 = '784227709496942592-tN2jA5rQTDw8Bepf4Z1inc5yQnOR2Nf'
    aks3 = 'OGZ2zlNl0vOw5lo2fXRnrLV02bcWj3eau9TJViYbnkJf5'
    auth3 = OAuthHandler(ck3, cs3)
    auth3.set_access_token(ak3, aks3)
    api3 = tweepy.API(auth3)
    api = [api1,api2,api3]
    return api
    
'''
this is the download tweets part
'''
def search(query,s,t,api):
    Tweets = tweepy.Cursor(api.search,q = query,lang ='en',since=s, max_id=t,count=100,retweet=False).items()
    #Tweets = tweepy.Cursor(api.search,q = query,lang ='en',since=s, until=t,count=100,retweet=False).items()
    filename = str(query+s+'.txt')
    f = open(filename, 'ab')
    while True:
        try:
            tweet = Tweets.next()
            name = tweet.author.name.encode('utf8')
            date = str(tweet.created_at)
            text = tweet.text.encode('utf8')
            id = str(tweet.id_str)
            try:
                info = id + "|#***#|" + name + "|#***#|" + date + "|#***#|" + text + "\r\n"
                f.write(info)
            except:
                print 'io error'
        except tweepy.TweepError:
            print 'need waiting'
            time.sleep(60*17)
            continue
        except IOError:
            print 'io error'
            time.sleep(60*2.5)
            continue
        except StopIteration:
            break
    f.close()
   
api = getapi()    
#search("#trump","2016-10-29","2016-10-30",api[0])
search("#trump","2016-11-01","802021975950577665",api[0])
#search("#trump","2016-10-30","2016-11-01",api[2])



