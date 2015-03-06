#!/usr/bin/env python

import pika

import sys

import email

b = email.message_from_string(''.join(sys.stdin.readlines()))

message = ''

if b.is_multipart():

for payload in b.get_payload():

# if payload.is_multipart(): ...

message = payload.get_payload()

else:

message = b.get_payload()

credentials = pika.PlainCredentials('demo', 'Wq25jvpA6e~T8Cn')

connection = pika.BlockingConnection(pika.ConnectionParameters(

host='162.252.80.98',credentials=credentials))

channel = connection.channel()

#channel.exchange_declare(exchange='python-test',

# type='fanout')

# message = sys.stdin.readlines() #' '.join(sys.argv[1:]) or "info: Hello World!"

channel.basic_publish(exchange='zenoss-mails',

properties=pika.BasicProperties(content_type='text/plain',

delivery_mode=1),

routing_key='zenoss-mail-queue',

body= ''.join(message))

print " [x] Sent %r" % (''.join(message),)

connection.close() 