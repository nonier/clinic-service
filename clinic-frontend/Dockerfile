#FROM node
#RUN mkdir -p /app
#WORKDIR /app
#COPY . .
#RUN npm install
#RUN npm run build
#EXPOSE 8080

#STAGE 1
FROM node AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

#STAGE 2
FROM nginx:1.17.1-alpine
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/clinic-frontend /usr/share/nginx/html
