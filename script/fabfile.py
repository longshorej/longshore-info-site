from fabric.api import env
from fabric.contrib import project
from fabric.operations import run, put

env.hosts = ["longshore.info"]
env.user = "root"

def bootstrap():
    run("echo 'deb http://mirror.rackspace.com/debian jessie-backports main' > /etc/apt/sources.list.d/jessie-backports.list")
    run("apt-get -y update")
    run("apt-get -y install openjdk-8-jre-headless screen vim")

def deploy(path):
    project.rsync_project("~/longshore-info-site", path, delete=True)
    run("chmod +x ~/longshore-info-site/pack/bin/main")
    run("screen -S longshore-info-site -X quit || true")
    run("screen -dm -S longshore-info-site ~/longshore-info-site/pack/bin/main 80")