FROM postgres:12.4
RUN sed -i 's/^# *\(en_US.UTF-8\|de_DE.UTF-8\)/\1/' /etc/locale.gen && \
        locale-gen
ENV LANG en_US.UTF-8

ENV POSTGRES_DB postgres

MAINTAINER Elias Meireles
ENV PORT 5432
ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD 12345678
ENV TZ=UTC
EXPOSE $PORT