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
    project.rsync_project("~/longshore-info-site.deb", path, delete=True)
    run("dpkg -i ~/longshore-info-site.deb")
    run("iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080")