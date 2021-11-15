Before starting the app make sure that `challenges-demo` namespace exists. Checking:

```shell
~ kubectl get namespaces
NAME              STATUS   AGE
challenges-demo   Active   27s
default           Active   53d
kube-node-lease   Active   53d
kube-public       Active   53d
kube-system       Active   53d
```

If not exists, create using the following command: 

```shell
~ kubectl create namespace challenges-demo
```