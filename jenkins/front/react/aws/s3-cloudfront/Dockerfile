FROM jenkins/inbound-agent:latest

USER root

# install packages
RUN apt-get -y update \
    && apt-get -y upgrade \
    && apt-get -y install sudo curl bash jq python3 python3-pip \
    && apt-get -y install git
RUN whereis git

# install AWS CLI
RUN set +x \
  && pip3 install awscli --upgrade

# install nodejs and npm
ENV NODE_VERSION 16.x
RUN curl -fsSL "https://deb.nodesource.com/setup_${NODE_VERSION}"| bash -\
  && apt-get install -y nodejs \
  && node --version && npm --version

