## build stage
#FROM node:14.14.0 as build-stage
#WORKDIR /app
#COPY package*.json ./
#RUN npm install
#COPY . .
#RUN npm run build
#
## production stage
#FROM nginx:stable-alpine as production-stage
#COPY nginx.conf /etc/nginx/conf.d/configfile.template
#
#COPY --from=build-stage /app/dist /usr/share/nginx/html
#
#ENV PORT 8848
#ENV HOST 0.0.0.0
#EXPOSE 8848
#CMD sh -c "envsubst '\$PORT' < /etc/nginx/conf.d/configfile.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/configfile.template



ENV PORT 8081
ENV HOST 0.0.0.0
EXPOSE 8081
CMD sh -c "envsubst '\$PORT' < /etc/nginx/conf.d/configfile.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"