#!/usr/bin/env python
import pika
import sys

credentials = pika.PlainCredentials('cloud', 'cloud')
connection = pika.BlockingConnection(pika.ConnectionParameters(
        host='192.168.1.200',credentials=credentials))
channel = connection.channel()

#channel.exchange_declare(exchange='python-test',
#                        type='fanout')

message = sys.stdin.readlines() #' '.join(sys.argv[1:]) or "info: Hello World!"

channel.basic_publish(exchange='zenoss-mails',
			properties=pika.BasicProperties(content_type='text/plain',
                                                                 delivery_mode=1),
                      routing_key='test-qu',
                      body= ''.join(message))
print " [x] Sent %r" % (''.join(message),)
connection.close()
