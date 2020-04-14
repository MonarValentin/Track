DH (Data Highway)

*DH documentation* :
<https://git.ouroath.com/pages/dhrainbow/debugging/emitters/>

\`\`\`

\$ sudo /home/y/bin/dh_rainbow_streammon \|grep EventForwarder.totalEventsSent
&& sleep 20 && sudo /home/y/bin/dh_rainbow_streammon \|grep
EventForwarder.totalEventsSent && sleep 20 && sudo
/home/y/bin/dh_rainbow_streammon \|grep EventForwarder.totalEventsSent

\`\`\`

*Triangular issue:* dh_rainbow_debug

*To kill the "dh_rainbow_eventforwarder" process:*

\`\`\`

\$ sudo killall dh_rainbow_eventforwarder

\$ sudo killall dh_rainbow_eventdespooler

\`\`\`

*Verify:*

If it is really shut down properly, this command will not show any output:

\`\`\`

\$ pgrep -f /home/y/bin64/dh_rainbow_eventforwarder \`\`\`

OR

\`\`\`

\$ sudo svstat /home/y/var/service/fac20-dh_rainbow_eventforwarder

\`\`\`
