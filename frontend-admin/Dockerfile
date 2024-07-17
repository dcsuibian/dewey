FROM nginx:1.25.4-alpine
LABEL authors="dcsuibian"

COPY dist/ /usr/share/nginx/html
COPY deploy/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 7309
