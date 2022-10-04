import sys
import json

with open(sys.argv[1], 'r') as inf:
    d = json.load(inf)


filters = ['user', 'slingshot', 'gniazdo', 'deconz','core','clojure','com.faster','clj_http','cheshire']
o = []

for i in d:
    filter_out = False

    for f in filters:
        if i['name'].startswith(f):
            filter_out = True

    if filter_out is False:
        o.append(i)

print(json.dumps(o, indent=1))
