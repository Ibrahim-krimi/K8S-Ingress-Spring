#!/bin/sh

# Remplacer les URLs dynamiquement dans les fichiers Angular générés
sed -i "s|http://localhost:8080|$USER_SERVICE_URL|g" /usr/share/nginx/html/main.*.js
sed -i "s|http://localhost:8081|$PRODUCT_SERVICE_URL|g" /usr/share/nginx/html/main.*.js
sed -i "s|http://localhost:8082|$ORDER_SERVICE_URL|g" /usr/share/nginx/html/main.*.js

nginx -g 'daemon off;'
