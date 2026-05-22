# docker 이미지 파일을 만드는 형식 => Dockerfile 파일명
# 도커 이미지 는 레이어 단위로 구성
# 가장 중요하고 가장 먼저 선언 할것은 어떤 이미지를 가지고 시작할것인지 결정
FROM nginx:alpine

# 이미지 만들때 파일을 복사해라
# COPY 로컬컴퓨터소스폴더 이미지내의대상폴더
COPY ./webpage /usr/share/nginx/html
