


/**
    *ProgramFlow pathfinder
    *Fact list of interconnected nodes.
    *The purpose of the "xloop" atoms is to get around the cycling issue, to prevent
    *cycling we used the not member predicate to make sure nothing appeared on the list of results more than once.
    *However we need to simulate while and for loops in our DD-Path diagram, so for the purpose of a workaround, any decision involving a loop
    *has been entered twice, with and without the suffix "Loop", you can regard those with "Loop" attached as the return from a loop.
**/


/** 
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
**/


path(start, a, 1).
path(a, b, 1).
path(a, end, 1).
path(b, aLoop, 1).
path(b, end, 1).
path(a, e, 1).
path(aLoop, end, 1).
path(b, c, 1).
path(c, d, 1).
path(d, cLoop, 1).
path(cLoop, aLoop, 1).
path(e, f, 1).
path(f, eLoop, 1).
path(eLoop, g, 1).
path(g, aLoop, 1).
path(g, h, 1).
path(h, i, 1).
path(h, j, 1).
path(i, aLoop, 1).
path(j, aLoop, 1).



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
    route(start,end , R, N).
    R = [start, a, end],
    N = 2 ;
    R = [start, a, b, end],
    N = 3 ;
    R = [start, a, b, aLoop, end],
    N = 4 ;
    R = [start, a, b, c, d, cLoop, aLoop, end],
    N = 7 ;
    R = [start, a, e, f, eLoop, g, aLoop, end],
    N = 7 ;
    R = [start, a, e, f, eLoop, g, h, i, aLoop, end],
    N = 9 ;
    R = [start, a, e, f, eLoop, g, h, j, aLoop, end],
    N = 9 ;


*/
