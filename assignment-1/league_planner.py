
file=open('example_graph_3.txt')

save_txt=[]
total_matches_number=0    

for line in file:
	total_matches_number=total_matches_number+1    #counts the total lines that are the total matches from the import file.
	save_txt.append(line)

file.close()						
		
match_plan = [[-1 for rows in range(3)] for columns in range(total_matches_number)] #create an array full of -1 in the dimention number of lines in input txt and number of columns 3.

for line in range(total_matches_number):
	match_plan[line][0],match_plan[line][1]=save_txt[line].split()    #create an array that contains for example(a,b,-1). a is the first team b the second and -1 is the day that is not yet planned
		
day=-1     #initialize the days 
planned=0   #initialize the plannes matches 
	
	
while planned<total_matches_number :     #checks if all matches that have been inserted have been planned
	
	data_matches=[]
	data_matches[:]=[]  #empty data_matches because the day has changed too
	day=day+1    #start planning for the next day
	
	for line in range(len(match_plan)):
		
		currentLine_available_teams=0     #counter that gets initialized in every line, with max value 2 (because there are 2 teams per line).
			
		for column in range(2) :     #column 3 is going to change later if i plan the match.
			
	
			if (match_plan[line][column] not in data_matches) and match_plan[line][2]==-1 : 				

				currentLine_available_teams=currentLine_available_teams+1 
					
			if currentLine_available_teams==2:			
					data_matches.append(match_plan[line][0])     #insert the team into the list so it doesn't play again the same day.
					data_matches.append(match_plan[line][1])
					match_plan[line][2]= day	
					planned=planned+1

day_counter=-1 

while day_counter <= day :    #using this variable (day_counter) so i know that i have searched for all the days that have been planned in the while loop above.
	
	day_counter=day_counter+1

	for line in range(len(match_plan)) :

			if match_plan[line][2]==day_counter:  #just cheching the last column in which the day that the match has been planned is saved and then print it and move on.
					print("(" + match_plan[line][0] + "," + match_plan[line][1] +")"+ str(match_plan[line][2]))
