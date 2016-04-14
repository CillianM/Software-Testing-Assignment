/**
    *ProgramFlow pathfinder
    *Fact list of interconnected nodes.
    *The purpose of the "xloop" atoms is to get around the cycling issue, to prevent
    *cycling we used the not member predicate to make sure nothing appeared on the list of results more than once.
    *However we need to simulate while and for loops in our DD-Path diagram, so for the purpose of a workaround, any decision involving a loop
    *has been entered twice, with and without the suffix "Loop", you can regard those with "Loop" attached as the return from a loop.
**/


 
path(start, "1", 1).
path("1", "2", 1).
path("2", "3", 1).
path("3", "4", 1).
path("4", "5", 1).
%path("4", "19", 1). %comment this one out to generate 2nd shortest route
path("4Loop", "19", 1).
path("5", "6", 1).
path("5", "9", 1).
path("6", "7", 1).
path("7", "8", 1).
path("8", "7Loop", 1).
path("7Loop", "4Loop", 1).
path("9", "10", 1). 
path("10", "11", 1).
path("11", "10Loop", 1).
path("10", "12", 1).
path("10Loop", "12", 1). 
path("12", "18", 1).
path("18", "4Loop", 1).
path("12", "13", 1).
path("13", "14", 1).
path("14", "15", 1).
path("15", "16", 1).
path("16", "4Loop", 1).
path("15", "17", 1).
path("17", "4Loop", 1).  
path("19", finish, 1).


%function route to calculate all possible routes from facts above
route(StartNode, EndNode, Route, NDist) :-                              
    route(StartNode, EndNode, [StartNode], 0, Route, NDist). 
    
route(StartNode, EndNode, Visited, DistAcc, Route, NDist) :-    
    path(StartNode, EndNode, Dist),                                     
    reverse([EndNode|Visited] , Route),                                               
    NDist is DistAcc + Dist.                                
        
route(StartNode, EndNode, Visited, DistAcc, Route, NDist) :-
    path(StartNode, Midpoint, Dist),
    not(member(Midpoint, Visited)),                                                         
    NewDistAcc is DistAcc + Dist,                                            
    route(Midpoint, EndNode, [Midpoint|Visited], NewDistAcc, Route, NDist).  

%returns shortest route from list of ordered routes.    
shortest(StartNode, EndNode, Route, NDist) :-
    setof((NDist, Route), route(StartNode, EndNode, Route, NDist), [(NDist, Route)|_]).    
    



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
