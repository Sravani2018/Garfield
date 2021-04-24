from flask import Flask, request, jsonify
import requests
import string
import webbrowser
from lxml import html
from bs4 import BeautifulSoup
import aiml
import os

app = Flask(__name__)

kernel = aiml.Kernel()
kernel.learn("AIML.xml")
kernel.respond("load aiml b")

try: 
    from googlesearch import search 
except ImportError: 
    print("No module named 'google' found")

@app.route('/', methods=['POST'])
def home():
    msg = (request.form['msg'])
    address = (request.form['address'])
    username = (request.form['username'])
    Doc = (request.form['d'])
    DocNo = (request.form['dn'])
    Emergency = (request.form['ec'])
    EmergencyNo =  (request.form['ecn'])
    PersonalNo = (request.form['pn'])
    Response = BOT(msg,username,address,Doc,DocNo,Emergency,EmergencyNo,PersonalNo)
    return Response



def chatbot_query(query, index=0):
    fallback = 'Sorry, I cannot think of a reply for that.'
    result = ''

    try: 
        from googlesearch import search 
    except ImportError: 
        print("No module named 'google' found")
    try:
        global search_list
        search_list = list(search(query, tld="co.in", num=10, stop=3, pause=2))

        page = requests.get(search_list[index])

        tree = html.fromstring(page.content)

        soup = BeautifulSoup(page.content, features="lxml")

        article_text = ''
        article = soup.findAll('p')
        for element in article:
            article_text += '\n' + ''.join(element.findAll(text = True))
        article_text = article_text.replace('\n', '')
        first_sentence = article_text.split('.')
        first_sentence = first_sentence[0].split('?')[0]

        chars_without_whitespace = first_sentence.translate(
            { ord(c): None for c in string.whitespace }
        )

        if len(chars_without_whitespace) > 0:
            result = first_sentence
        else:
            result = fallback
        return result
    except:
        if len(result) == 0: result = fallback
        return result



def BOT(query, usn, addr, doc, docNo, Emergency, EmergencyNo, PersonNo):
    user_input = query.lower()
    user_name = usn.upper()
    address = addr
    Doc = doc
    DocNo = docNo
    Emerg = Emergency
    EmergNo = EmergencyNo
    PNo = PersonNo 

    
    punctuations = '''!()-[]{};:'"\,<>./?@#$%^&*_~'''

    for x in user_input : 
        if x in punctuations: 
            user_input = user_input.replace(x, "") 

    # Print string without punctuations
    if 'google' in user_input :
        user_input = user_input.replace('google', "")
        return chatbot_query(user_input, index=0)

    # if 'my name' in user_input :


    elif 'time' in user_input :
        from datetime import datetime
        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
        return 'The time is ' + current_time

    elif 'you' in user_input:
        return 'I am Garfield, your memory assistant'

    elif 'who am i' in user_input:
        return 'You are ' + user_name
 
    elif 'name' in user_input:
        return 'Your name is ' + user_name   

    elif 'address' in user_input:
        return address

    elif Emerg in user_input:
        return "Should I call " + Emerg
    
    elif 'doctor' in user_input:
        return "Should I call the Doctor " + Doc

    elif Doc in user_input:
        return "Should I call the Doctor " + Doc    
    
    elif 'my number' in user_input:
        return PNo

    elif 'home' in user_input:
        return 'You stay here: ' + address + kernel.respond(user_input)

    else:
        return kernel.respond(user_input)

    