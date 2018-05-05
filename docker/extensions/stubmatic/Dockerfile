FROM node:6-alpine

RUN npm install stubmatic -g
WORKDIR /usr/src/app
EXPOSE 7777

CMD ["stubmatic", "-v", "-d", "/usr/src/app", "-c", "config.json"]
