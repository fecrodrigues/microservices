#Base Image
FROM python:3.9
#Set enviroment variables
ADD . /code
WORKDIR /code
# Install dependencies
RUN pip3 install --upgrade pip
RUN pip3 install -r requirements.txt
CMD ["python", "main.py"]
