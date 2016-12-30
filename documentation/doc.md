# Documentation

Dvidder is a simple social media app that let's you share thoughts with people who you have given your username. (Or people who search for tags you've used)

## Use cases

### Login

When user requests the site unauthenticated they will be redirected to login page. In login page, user enters login details (username and password) and submits login form. If login was successful, user will be directed to the actual app. If login was unsuccessful user will be informed about the error.

### Register

Login page has a link directing the user to /register. In this page user can enter new account details and optional profile picture to create a new account. After account creation user will be directed back to login page.

### Logout

There is a link to logout at the top of the app page. Clicking that will log a user out and lead to login page.

### Post a dveed

Once user has logged in, they can write a dveed in a textarea and give it tags in another text input. After submission the user has to either search by their username or any tag they used if they want to see the dveed.

### Search by username

Below the dveed creation form there is search by user form. User can enter any username and find dveeds by any user whose username they know. 

### Search by tag

Below search by user form there is another form used to search by tag. User can enter one tag in this input and search for all dveeds that include the given tag.

### Like dveed

If user has found dveeds by searching, they can be liked. At the bottom of all dveeds there is a like link. Once clicked the like appears in parenthesis next to the link.

### Dislike dveed

Just like liking but disliking.

### Delete dveed

Next to liking and disliking links there is a delete link if you created the dveed or if your account is admin (admin accounts can't be created through the UI). Clicking delete will delete the dveed.

### Delete account

Admins also have delete account link in every dveed. Clicking that link will delete the account and all dveeds of the account that created this dveed. 

## Uninplemented features

### Commenting

Users can comment on dveeds with a separate commenting form attached to every dveed.

### Follow feed

Users mark any tag or other users as followed and all dveeds by followed users or dveeds that has tags marked as your followed tags will appear as default below search forms. Feed will be replaced with whatever a user searches. 
