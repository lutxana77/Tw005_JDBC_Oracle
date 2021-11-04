
SET SERVEROUT ON;
create or replace  PROCEDURE MI_PROGRAMA(
    C_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE,
    C_FNAME OUT employees.first_name%TYPE,
    C_LNAME OUT employees.last_name%TYPE
)
DECLARE
    --DECLARO VARIABLE--
    c_id employees.employee_id%TYPE := 100;--asigna valor 100 
    c_fname employees.first_name%TYPE;--coge el tipo que corresponda
    c_lname employees.last_name%TYPE;
    
BEGIN
    --QUERY--
    select first_name, last_name INTO c_fname, c_lname
       FROM employees 
       WHERE employee_id = c_ID;
       
       DBMS_OUTPUT.put_line('...HOLA ' ||c_fname||' '||c_lname);
END;