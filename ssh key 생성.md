### master
``` bash
ssh-keygen -t rsa
cat id_rsa.pub >> $HOME/.ssh/authorized_keys
chmod 600 id_rsa
chmod 644 id_rsa.pub
```

### slave
``` bash
cat id_rsa.pub >> $HOME/.ssh/authorized_keys
chmod 600 $HOME/.ssh/authorized_keys
chmod 700 $HOME/.ssh/
```
