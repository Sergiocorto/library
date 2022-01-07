async function getAllAuthors() {

    const res = await requestToBackend("/api/author", 'GET')

    if (!checkIsError(res)) {
        res.data.forEach(author => {
            $('.body').append(createAuthorCard(author));
        });
    }
}

getAllAuthors()

//Function for adding author via fetch request
$('#add_author').on('submit', async function (e) {
    e.preventDefault()

    const res = await requestToBackend($(this).attr('action'), 'POST', new FormData(this))

    if (!checkIsError(res)) {
        alertShow($('#successAlert'))
        $('.body').append(createAuthorCard(res.data))
    }
})

$('#edit_author').on('submit',async function (e) {
    e.preventDefault()

    const res = await requestToBackend($(this).attr('action'), $(this).attr('method'), new FormData(this))

    if (!checkIsError(res)) {
        alertShow($('.alert-success'))
        editAuthorCard(res.data)
    }
});

//Author card editing function
function editAuthorCard(author) {
    $(`.author-${author.id}`).children("p[id='lastname']").text(author.lastname)
    $(`.author-${author.id}`).children("p[id='name']").text(author.name)
    $(`.author-${author.id}`).children("p[id='patronymic']").text(author.patronymic)
}


//Function to open the author edit window
function openAuthorForm(btn) {

    let authorForm = $(btn).parent().parent()

    let authorNameBox = authorForm.children(".authorName")
    console.log(authorNameBox.children("input[name='id']").val())
    $("input[name='id']").val(authorNameBox.children("input[name='id']").val())
    $("input[name='lastname']").val(authorNameBox.children("p[id='lastname']").text())
    $("input[name='name']").val(authorNameBox.children("p[id='name']").text())
    $("input[name='patronymic']").val(authorNameBox.children("p[id='patronymic']").text())
}

//Function for sending a request to delete an author
async function deleteAuthor(btn) {

    const res = await requestToBackend($(btn).data('link'), 'DELETE')

    if(!checkIsError(res)) {
        alert('Автор удален')
        $('.author-' + res.data).remove()
    }
}

//Author card creation function
function createAuthorCard(author) {
    return $("<div class=\"card w-100 author-" + author.id + "\">\n" +
        "<div class=\"card-body\">\n" +
        "<form name=\"authorForm\">\n" +
        "<div class=\"authorCard authorName author-" + author.id + "\">\n" +
        "<p class=\"name\" id=\"lastname\" onformdata=\"lastname\">" + author.lastname + "</p>\n" +
        "<p class=\"name\" id=\"name\">" + author.name + "</p>\n" +
        "<p class=\"name\" id=\"patronymic\">" + author.patronymic +"</p>\n" +
        "<input class=\"name\" name=\"id\" readOnly type=\"hidden\" value=\"" + author.id + "\">\n" +
        "</div>\n" +
        "<div class=\"btn-group btn-card authorCard\">\n" +
        "<button type=\"button\" class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"openAuthorForm(this)\" data-target=\"#editModalWindow\">\n" +
        "Edit\n" +
        "</button>\n" +
        "<button type=\"button\" class=\"btn btn-danger\" onClick=\"deleteAuthor(this)\" data-link=\"/api/author/?id="+ author.id +"\">\n" +
        "Delete\n" +
        "</button>\n" +
        "</div>\n" +
        "</form>\n" +
        "</div>\n" +
        "</div>")
}

function successAddAuthor () {
    $('.alert-add-author').css('display','block');
    setTimeout(function(){
        $('.alert-add-author').css('display','none');
    }, 3000);
}

function errorRequest(msg){
    console.log(JSON.stringify(msg));
    alert('Запись не удалась');
}