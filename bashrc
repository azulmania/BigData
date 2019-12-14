# .bashrc

# User specific aliases and functions

alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'

# Source global definitions
if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi
export ACCUMULO_HOME=/var/lib/accumulo
export PATH=/opt/cloudera/parcels/Anaconda/bin:$HOME/sbin:$PATH
#export PYSPARK_DRIVER_PYTHON=ipython
#export PYSPARK_DRIVER_PYTHON_OPTS='notebook --ip gateway --no-browser'
#export PYSPARK_PYTHON=/opt/cloudera/parcels/Anaconda/bin/python
export DEVSH=/home/training/training_materials/devsh
export DEVDATA=/home/training/training_materials/devsh/data
# Uncomment this line to use Jupyter Notebook for pyspark (then open a new terminal window)
# export PYSPARK_DRIVER_PYTHON_OPTS='notebook --ip gateway --no-browser' 
export KUDU=/home/training/training_materials/kudu
export KDATA=/home/training/training_materials/kudu/data
export PATH=/usr/local/apache-maven/apache-maven-3.2.1/bin:$PATH
export ACCUMULO_HOME=/var/lib/accumulo
## Config added for Spark 2.4.4
export SPARK_HOME=/home/training/spark
export PYSPARK_PYTHON=/home/training/anaconda3/bin/python
export PATH=$SPARK_HOME/bin:$PATH
#export HADOOP_HOME=$SPARK_HOME/hadoop
export PYSPARK_DRIVER_PYTHON=jupyter 
export PYSPARK_DRIVER_PYTHON_OPTS=notebook



# >>> conda initialize >>>
# !! Contents within this block are managed by 'conda init' !!
__conda_setup="$('/home/training/anaconda3/bin/conda' 'shell.bash' 'hook' 2> /dev/null)"
if [ $? -eq 0 ]; then
    eval "$__conda_setup"
else
    if [ -f "/home/training/anaconda3/etc/profile.d/conda.sh" ]; then
        . "/home/training/anaconda3/etc/profile.d/conda.sh"
    else
        export PATH="/home/training/anaconda3/bin:$PATH"
    fi
fi
unset __conda_setup
# <<< conda initialize <<<

