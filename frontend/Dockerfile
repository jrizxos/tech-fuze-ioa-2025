FROM node:22
WORKDIR /app
COPY frontend/package*.json .
RUN npm install
RUN npm install serve -g
COPY frontend/ .
COPY misc/layer_positions.json .
RUN npm run build