# k8s-training
## Original Article
This was all lifted from this [Skaffold article](https://dzone.com/articles/skaffold-k8s-development-made-easy) but
installing for `macOS` with brew (instead of Ubuntu)
## Install
```
brew install minikube
```
```
brew install kubectl
```
```
brew install skaffold
```
## Use Skaffold
Just follow steps of original [article](https://dzone.com/articles/skaffold-k8s-development-made-easy),
replacing app name and image name accordingly.

## Invoke Service
Get minikube ip
```
minikube ip
192.168.64.2
```
View Services
```
kubectl get services
NAME         TYPE        CLUSTER-IP    EXTERNAL-IP   PORT(S)          AGE
kubernetes   ClusterIP   10.96.0.1     <none>        443/TCP          4h3m
things       NodePort    10.96.1.178   <none>        8080:31491/TCP   77m
```
Curl It
```
curl -s http://192.168.64.2:31491/things/137 | jq .
{
  "java": {
    "vendor": "Oracle Corporation",
    "version": "11.0.5",
    "home": "/usr/local/openjdk-11"
  },
  "os": {
    "name": "Linux",
    "arch": "amd64",
    "version": "4.19.94"
  },
  "ip": "things-86d49cbd59-wjkzc",
  "id": 137,
  "user": {
    "name": "nobody",
    "dir": "/",
    "home": "/nonexistent"
  },
  "ts": 1585184478247
}
```
