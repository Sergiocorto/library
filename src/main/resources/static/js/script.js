//Function for sorting authors/books
//The function takes the pressed button as a parameter
async function sort(btn) {

    const res = await requestToBackend($(btn).data('url'), 'GET')

    if (!checkIsError(res)) {
        refreshList(res.data, $(btn).data('flag'))
    }
}

//Search function for authors/books
async function search(btn) {
//Create variable request addresses
    let url = $(btn).data('url') + $('#search').val()

    const res = await requestToBackend(url, 'GET')

    if (!checkIsError(res)) {
        refreshList(res.data, $(btn).data('flag'))
    }
}

//Function on / off the search button depending on the presence of text in the search input field
$('#search').on('input', function () {
    if($(this).val()){
        $('#searchBtn').removeAttr('disabled')
    }else{
        $('#searchBtn').attr('disabled', 'disabled')
    }
})

//function of updating the list of authors / books
function refreshList(array, flag) {
    
    switch (flag) {
        case 'authors' :
            $('.card').remove()
            array.forEach((author) => {
                $('.body').append(createAuthorCard(author))
            })
            break
        case 'books' :
            $('.card').remove()
            array.forEach((book) => {
                $('.body').append(createBookCard(book))
                $('.authors').append(showAuthor(book.authors, book.title))
            })
            break
    }
}

//Info message display function
function alertShow(alert) {
        alert.css('display', 'block')
        setTimeout(function(){
            alert.css('display', 'none')
        }, 3000)
}

//Function for request to backend
async function requestToBackend(url, method, body = null) {

    const res = {}
    await fetch(url, {
        method: method,
        body: body
    })
        .then(response => response.json())
        .then(data => res.data = data)
        .catch(e => res.e = e)
    return res
}

//Function checking for an error in response
function checkIsError(res) {
    if (res.e) {
        console.log(e)
        alertShow($('#errorAlert'))
        return true
    }
}