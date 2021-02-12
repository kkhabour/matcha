#!/bin/bash


DOCKER_HOME="$HOME/.docker"
DOCKER_DEST="$HOME/goinfre/.docker"

BREW_HOME="$HOME/brew"
BREW_DEST="$HOME/goinfre/brew"

# check if brew installed

if ! command -v brew &> /dev/null
then
		echo "installing brew"
		git clone https://github.com/Homebrew/brew.git $BREW_DEST
fi

# add brew to PATH
echo 'export PATH=/Users/kkhabour/goinfre/brew/bin/:$PATH' >> /Users/kkhabour/.zshrc
source /Users/kkhabour/.zshrc

if ! command -v docker &> /dev/null
then
	echo "installing docker..."
	brew install docker
fi

if ! command -v docker-machine &> /dev/null
then
	echo "installing docker-machine..."
	brew install docker-machine
fi

if ! command -v docker-compose &> /dev/null
then
	echo "installing docker-compose..."
	brew install docker-compose
fi


# Link .docker to goinfre
if [ -d $DOCKER_HOME ]
then
	mv $DOCKER_HOME $DOCKER_DEST
	ln -s $DOCKER_DEST $DOCKER_HOME
else
	mkdir -p $DOCKER_DEST
	ln -s $DOCKER_DEST $DOCKER_HOME
fi




# create (or start if existing) the default docker machine
if docker-machine ls | grep -q 'matcha'
then
    docker-machine start matcha
else
    docker-machine create --driver virtualbox matcha
fi

