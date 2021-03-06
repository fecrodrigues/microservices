# Escolhando a imagem base
FROM node:14.16-alpine

# Informando diretorio de trabalho
WORKDIR /app

# Adicionando variaveis de ambiente necessarias
ENV PATH /app/node_modules/.bin:$PATH

# Instalando as dependencias do projeto
COPY package.json ./
COPY package-lock.json ./
RUN npm install

COPY . ./

CMD ["npm", "start"]