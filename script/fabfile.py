from fabric.api import env
from fabric.operations import run, put

env.hosts = ["longshore.info"]
env.user = "root"

def bootstrap():
    run("echo 'deb http://mirror.rackspace.com/debian jessie-backports main' > /etc/apt/sources.list.d/jessie-backports.list")
    run("apt-get -y update")
    run("apt-get -y install openjdk-8-jre-headless screen vim")

def deploy(path):
    run("screen -S longshore-info-site -X quit || true")
    run("rm -rf ~/longshore-info-site")
    run("mkdir -p ~/longshore-info-site")
    put(path, "~/longshore-info-site")
    run("chmod +x ~/longshore-info-site/pack/bin/main")
    run("screen -dm -S longshore-info-site ~/longshore-info-site/pack/bin/main 80")