:- multifile equipment/1, type/1.
/* 
 * These two statements must be evalueted before 
 * those from KnowledgeBase.pl file
 *
 ****************************************************/

type(Type):- required_type(Type), inc_checked, false.
equipment(Level):- required_equipment(Level), inc_checked, false.