


/**
    *ProgramFlow pathfinder
    *Fact list of interconnected nodes.
    *The purpose of the "xloop" atoms is to get around the cycling issue, to prevent
    *cycling we used the not member predicate to make sure nothing appeared on the list of results more than once.
    *However we need to simulate while and for loops in our DD-Path diagram, so for the purpose of a workaround, any decision involving a loop
    *has been entered twice, with and without the suffix "Loop", you can regard those with "Loop" attached as the return from a loop.
**/


 
road(start, "1", 1).
road("1", "2", 1).
road("2", "3", 1).
road("3", "4", 1).
road("4", "5", 1).
%road("4", "19", 1). %comment this one out to generate 2nd shortest route
road("4Loop", "19", 1).
road("5", "6", 1).
road("5", "9", 1).
road("6", "7", 1).
road("7", "8", 1).
road("8", "7Loop", 1).
road("7Loop", "4Loop", 1).
road("9", "10", 1). 
road("10", "11", 1).
road("11", "10Loop", 1).
road("10", "12", 1).
road("10Loop", "12", 1). 
road("12", "18", 1).
road("18", "4Loop", 1).
road("12", "13", 1).
road("13", "14", 1).
road("14", "15", 1).
road("15", "16", 1).
road("16", "4Loop", 1).
road("15", "17", 1).
road("17", "4Loop", 1).  
road("19", finish, 1).


%function route to calculate all possible routes from facts above
route(StartTown, EndTown, Route, NDist) :-                              
    route(StartTown, EndTown, [StartTown], 0, Route, NDist). 
    
route(StartTown, EndTown, Visited, DistAcc, Route, NDist) :-    
    road(StartTown, EndTown, Dist),                                     
    reverse([EndTown|Visited] , Route),                                               
    NDist is DistAcc + Dist.                                
        
route(StartTown, EndTown, Visited, DistAcc, Route, NDist) :-
    road(StartTown, Midpoint, Dist),
    not(member(Midpoint, Visited)),                                                         
    NewDistAcc is DistAcc + Dist,                                            
    route(Midpoint, EndTown, [Midpoint|Visited], NewDistAcc, Route, NDist).  

%returns shortest route from list of ordered routes.    
shortest(StartTown, EndTown, Route, NDist) :-
    setof((NDist, Route), route(StartTown, EndTown, Route, NDist), [(NDist, Route)|_]).    
    



/**Usage as follows "route(start, finish, R, N)."
    Where R = Route taken
          N = Node cost


    R = [start, "1", "2", "3", "4", "5", "6", "7", "8", "7Loop", "4Loop", "19", finish],
    N = 12 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "11", "10Loop", "12", "18", "4Loop", "19", finish],
    N = 14 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "11", "10Loop", "12", "13", "14", "15", "16", "4Loop", "19", finish],
    N = 17 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "11", "10Loop", "12", "13", "14", "15", "17", "4Loop", "19", finish],
    N = 17 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "12", "18", "4Loop", "19", finish],
    N = 12 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "12", "13", "14", "15", "16", "4Loop", "19", finish],
    N = 15 ;
    R = [start, "1", "2", "3", "4", "5", "9", "10", "12", "13", "14", "15", "17", "4Loop", "19", finish],
    N = 15 ;


    [1] 12 ?- shortest(start, finish, R, N).
    R = [start, "1", "2", "3", "4", "19", finish],    This will only result if ladder of size 0 is entered is read in.
    N = 6.

    Actual shortest logical path
    [1] 14 ?- shortest(start, finish, R, N).
    R = [start, "1", "2", "3", "4", "5", "9", "10", "12", "18", "4Loop", "19", finish],
    N = 12.


*/
