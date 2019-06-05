#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import sys,heapq,operator
filename = sys.argv[0]#change 0 to 1
f = open("input1.txt",'r')
if f.mode == 'r':
    contents = f.read()
f.close()
#print(contents)
#Class for the Node
class Node:
    def __init__(self,data,name):
        self.name = name
        self.left_child = None
        self.right_child= None
        self.data = data
    def getData(self):
        return self.data
    def getASCII(self):
        return str(self.name)
    def __lt__(self, other):
        if self.data == other.data:
            return ord(self.name) < ord(other.name)
        return self.data < other.data
#Getting frequency of each word
dictn = {}
total_f = 0
total_lf = 0
#Make a dictionary with all frequencies
for i in contents:
    total_f = total_f + 1
    if i in dictn:
        x = dictn[i]
        x = x +1
        dictn[i] = x
    else:
        dictn[i] = 1
#Adding all nodes to min heap
h=[]
heapq.heapify(h)
for i in dictn:
    hn = Node(dictn[i],i)
    heapq.heappush(h,hn)

root = Node(0,None)
#print(dictn)

heapq.heapify(h)
#Make the tree
while len(h) > 1:
    x = heapq.heappop(h)
    ascii_x = ord(x.getASCII())
    y = heapq.heappop(h)
    ascii_y = ord(y.getASCII())
    data = x.getData() + y.getData()
    smaller_ascii = None
    if ascii_x < ascii_y:
        smaller_ascii = ascii_x
    else:
        smaller_ascii = ascii_y
    new_node = Node(data,chr(smaller_ascii))
    if x.getData() == y.getData():
        if smaller_ascii is ascii_x:
            new_node.left_child = x
            new_node.right_child = y
        else:
            new_node.right_child = x
            new_node.left_child = y
    else:
        new_node.left_child = x
        new_node.right_child = y
    root = new_node
    heapq.heappush(h,new_node)
    
#Get encoded values of all characters    
en_dictn = {}


def huffmanCodeToFile(n,s):
    if n is None:
        return
    if n.left_child is None and n.right_child is None:
        en_dictn[n] = s
    huffmanCodeToFile(n.left_child,s+"0")
    huffmanCodeToFile(n.right_child,s+"1")
    
#Storing the string in the encoded dictionary
huffmanCodeToFile(root,"")

#Calculating average
for i in en_dictn:
    total_lf = total_lf + (len(en_dictn[i]) * i.data)

ave = total_lf/total_f

#printing to code.txt
codefile = open("code.txt","w+")
for i in sorted(en_dictn,key = operator.attrgetter("name")):
    if i.name is ' ':
        codefile.write("{0}: {1} \n".format("Space",en_dictn[i]))
    else:
        codefile.write("{0}: {1} \n".format(i.name,en_dictn[i]))

codefile.write("Ave= {0:.2f} bits per symbol".format(ave))
codefile.close()

#printing to encodemsg.txt
encodefile = open('encodemsg.txt',"w+")
for i in contents:
    for j in en_dictn:
        if j.name is i:
            new_string = en_dictn[j]
            for k in new_string:
                encodefile.write(k)
                encodefile.write("\n")
encodefile.close()            