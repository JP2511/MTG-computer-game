#!/bin/bash

curl http://mythicspoiler.com/sets.html |
cat >>mainpage.txt
python creatingAListOfAllExpansions.py
rm mainpage.txt