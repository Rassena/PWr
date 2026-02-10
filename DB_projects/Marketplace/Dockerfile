FROM node:10

RUN git clone https://github.com/Rassena/nodejs-express-mysql.git
RUN mv nodejs-express-mysql /opt/app
WORKDIR /opt/app
VOLUME /tmp


RUN npm install
RUN npm install -g nodemon

EXPOSE 3000

CMD ["npm", "start" ]