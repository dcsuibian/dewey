FROM node:20.10.0-alpine
LABEL authors="dcsuibian"

WORKDIR /app
COPY public ./public
COPY .next/standalone .
RUN mkdir -p /app/.next/static
COPY .next/static ./.next/static

ENV NODE_ENV production
ENV PORT 7310
ENV HOSTNAME "0.0.0.0"

EXPOSE 7310
ENTRYPOINT ["node", "server.js"]
