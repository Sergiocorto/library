async function getAllBooks() {

    const res = await requestToBackend("/api/book", 'GET')
    console.log(res.data)
    if (!checkIsError(res)) {
        res.data.forEach(book => {
            $('.body').append(showBookCard(book));
        });
    }
}

getAllBooks()
addAuthorsToSelectList()

//Function for adding a book through a fetch request
$('#add_book').on('submit', async function (e) {
    e.preventDefault()
//Create an array for storing author IDs and put data from the form into it
    let authorsId = createAuthorIdArray($("span[name='author']"))
//Create form data variables
    let data = new FormData(this)
    data.append('authorsId', authorsId)

    const res = await requestToBackend($(this).attr('action'), 'POST', data)

    if(!checkIsError(res)) {
        alertShow($('#successAlert'))
        showBookCard(res.data)
    }
})

//Function of sending a request to delete a book
async function deleteBook(btn) {

    const res = await requestToBackend($(btn).data('link'), 'DELETE')

    if(!checkIsError(res)) {
        alert('Книга удалена' )
        $(`#book-${res.data}`).remove()
    }
}

//Book card creation function
function createBookCard(book) {

    return $(
        "<div id=\"book-"+ book.id +"\" class=\"card mb-8\" style=\"max-width: 740px;\">\n" +
            "<div class=\"row g-0\">\n" +
                "<div class=\"col-md-2\">\n" +
                    "<img class=\"book-img\" src=\"http://127.0.0.1:8887/" + book.image + "\">\n" +
                "</div>\n" +
                "<div class=\"col-md-8\">\n" +
                    "<div class=\"card-body " + book.id + "\">\n" +
                        "<h5 class=\"card-title\">" + book.title + "</h5>\n" +
                        "<p class=\"card-text\">" + book.description + "</p>\n" +
                        "<label>Авторы</label>\n" +
                    "</div>\n" +
                "</div>\n" +
                "<div class=\"col-md-2\">\n" +
                    "<div class=\"card-body\">\n" +
                        "<div class=\"btn-group btn-card\">\n" +
                            "<button type=\"button\" class=\"btn btn-danger\" onclick=\"deleteBook(this)\" data-link=\"/api/book/?id="+ book.id +"\">\n" +
                            "Delete\n" +
                            "</button>\n" +
                        "</div>\n" +
                    "</div>\n" +
                "</div>\n" +
            "</div>\n" +
        "</div>\n")
}
//The function of displaying authors in the book card when adding a new book
function showAuthor(authors, id) {
    return authors.forEach( (author) => {
        let authorName = '' + author.name + ' ' + author.lastname + ' ' + author.patronymic
        $(`.${id}`).append($('<span>', {
            class: 'badge badge-light',
            text: authorName
        }))
    })
}

//The function of creating a book card
//when a response from the server about the successful addition of a book to the database
function showBookCard(book) {
    $('.body').append(createBookCard(book))
    $('.authors').append(showAuthor(book.authors, book.id))
}

//Function to create an array of author IDs
function createAuthorIdArray(span) {
    let authorsId = []
    span.each(function (index){
        authorsId.push( Number($(this).attr('value')) )
    })
    console.log(authorsId)
    return authorsId
}

//The function of adding an author to the list of authors when creating a new book
function addToList(select){

    let authorName = select.options[select.selectedIndex].text

    $('#authorsList').append($('<span>', {
        class: 'badge badge-light',
        text: authorName,
        value: select.value,
        name: 'author'
    }))
}

//The function of adding an author to the list of authors when creating a new book
async function addAuthorsToSelectList(){

    const res = await requestToBackend("/api/author", 'GET')

    authorsArray = res.data

    res.data.forEach(author => {
        $('select[name="author"]').append($("<option value =\""+author.id+"\">"+author.lastname+" "+author.name+" "+author.patronymic+"</option>\n"))
    });
}