# auditing-tutorial
- Stwórz projekt. 
- Ustaw parametry DB + stwórz bazę w postgresql
- Stwórz encję Team.
- Oznacz encję adnotacjami:

@Audited

@EntityListeners(AuditingEntityListener.class)

- (To nie jest potrzebne, dopiero trzeba dodać jak ma być informacja o tym kto dodał/modyfikował rekord)Dodaj klasę z konfiguracją oraz adnotacją @EnableJpaAuditing - może być w klasie main()
- Dodaj repository do operacji CRUD.
- Dodaj Rest, którym będziesz mógł przetestować działanie - może być w klasie main()
- Uruchom aplikację

W najprostszej postaci wynikiem będzie utworzenie na bazie danych tabeli: team, team_aud (tabela audytowa) oraz tabeli z revision: revinfo.
Każda operacja Insert, Update, Delete będzie miała odzwierciedlenie w tabeli team_aud oraz revinfo.
W team_aud doda automatycznie kolummny: rev (relacja do revinfo) oraz revtype (0 - insert, 1 - update, 2 - delete).
Natomiast w revinfo znajdzie się timestamp operacji. 


## Rozszerzenie modelu o osobę tworzącą/modyfikującą rekord


select * from revinfo;
select * from team;
select * from team_aud;


select rev, revtstmp,
  to_char(
    to_timestamp ('01/01/1970 00:00:00','DD/MM/YYYY HH24:MI:SS')
    + (r.revtstmp /1000/60/60/24 ||' day')::interval, 'YYYY/MM/DD HH24:MI:SS'
  ) as rev_date, 
  to_char(
    TO_DATE('01/01/1970 00:00:00','DD/MM/YYYY HH24:MI:SS') 
    + (r.revtstmp /1000/60/60/24 ||' day')::interval, 'YYYY/MM'
  ) as rev_month 
from revinfo r;

## Links
- https://programmingmitra.blogspot.com/2017/02/automatic-spring-data-jpa-auditing-saving-CreatedBy-createddate-lastmodifiedby-lastmodifieddate-automatically.html 
- https://dzone.com/articles/spring-data-jpa-auditing-automatically-the-good-stuff
- https://blog.countableset.com/2014/03/08/auditing-spring-data-jpa-java-config/
- https://docs.spring.io/spring-data/jpa/docs/1.7.0.DATAJPA-580-SNAPSHOT/reference/html/auditing.html
- https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/config/EnableJpaAuditing.html
