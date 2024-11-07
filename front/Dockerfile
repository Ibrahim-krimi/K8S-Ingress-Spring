# Étape de build de l'application Angular
FROM node:18-alpine AS build-stage
WORKDIR /app

COPY package*.json ./
RUN npm install
COPY . .
RUN npm cache clean --force
RUN npm run build -- --configuration production

# Étape pour NGINX
FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build-stage /app/dist/front/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 80

ENTRYPOINT ["/entrypoint.sh"]
