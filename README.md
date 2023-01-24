# prediction-of-winnings

My solution to Sport Radar Coding Academy recruitment task. 

In this application, I have exposed two endpoints, one of them displays the most likely results of 10 teams by default http://localhost:8080/event, but you can specify a parameter in URL that limits the number of displayed events to the specified number for example http://localhost:8080/event?limit=777 this will display all the events that were fetched from the json file, I also implemented a method that makes it impossible to enter too many events. 
The second one endpoint displays table of team names in a Set<> structure which are sorted alphabetically http://localhost:8080/sort. I added team info display like Id team, Team Abbreviation, Country, teamName sorted alphabetically by teamName.

The data is fetched from the absolute path to data.json on my computer.

I also did unit tests mapDataFromJsonTest(); checks if the json file has been properly mapped

showEventTest(); checks the number of results whether it agrees with the given limit parameter

