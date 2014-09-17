 cd backend && gnome-terminal -e "mvn clean test spring-boot:run -Drun.jvmArguments=\"-Dspring.profiles.active=dev\"" & cd frontend && gnome-terminal -e "grunt serve"; cd ..
