# face2pay
This repo predicts the income of users based on a photo of their face

## Download files
You will require sql jdbc driver. Download from here. https://www.microsoft.com/en-in/download/details.aspx?id=11774

Run Main.java file to do the respective downloads. The comments explain which folders need to be there

The sql queries currently written are based on recency( sorted by userId desc ).
If the program gets stuck somewhere, the downloaded pictures need to be looked into
with the least userid and the query can be updated with another condition
'and u.UserID < {{the least userId}}'


