# mcd
## new branch
```
git init
git add .
git branch
git commit -m "init"
git remote add origin https://github.com/sai194/mcd.git
git remote -v
git push -u origin master
```
## rebase with main
```
git checkout origin/main
git checkout master 
git rebase -i origin/main
```

## resolve conflicts
```
git add .
git rebase --continue
git push -f
```