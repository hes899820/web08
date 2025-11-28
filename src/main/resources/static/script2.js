// 1. 아이디 입력창 정보 가져오기
let elInputUsername = document.querySelector('#username'); // input#username
// 2. 성공 메시지 정보 가져오기
let elSuccessMessage = document.querySelector('.success-message'); // div.success-message.hide
// 3. 실패 메시지 정보 가져오기 (글자수 제한 4~12글자)
let elFailureMessage = document.querySelector('.failure-message'); // div.failure-message.hide
// 4. 실패 메시지2 정보 가져오기 (영어 또는 숫자)
let elFailureMessageTwo = document.querySelector('.failure-message2'); // div.failure-message2.hide

// 1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#password'); // input#password
// 2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#password-retype'); // input#password-retype
// 3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
// 4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide

function idLength(value) {
    return value.length >= 4 && value.length <= 12
}

function onlyNumberAndEnglish(str) {
    return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
}

function strongPassword (str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

function isMatch (password1, password2) {
    return password1 === password2;
}

elInputUsername.onkeyup = function () {
    const value = elInputUsername.value;

    // 1. 값을 입력하지 않은 경우 (지웠을 때) -> 모두 숨김
    if (value.length === 0) {
        elSuccessMessage.classList.add('hide');
        elFailureMessage.classList.add('hide');
        elFailureMessageTwo.classList.add('hide');
        return; // 함수 종료
    }

    // 2. 영어 또는 숫자가 아닐 경우 (최우선 검사)
    if(onlyNumberAndEnglish(value) === false) {
        elSuccessMessage.classList.add('hide');
        elFailureMessage.classList.add('hide');
        elFailureMessageTwo.classList.remove('hide'); // 영어 또는 숫자만 가능
    }
    // 3. 글자 수가 4~12글자가 아닐 경우
    else if(idLength(value) === false) {
        elSuccessMessage.classList.add('hide');
        elFailureMessage.classList.remove('hide'); // 4~12글자이어야 함
        elFailureMessageTwo.classList.add('hide');
    }
    // 4. 모든 조건을 만족할 경우 (앞선 실패 조건을 모두 통과했으므로)
    else {
        elSuccessMessage.classList.remove('hide'); // 사용할 수 있는 아이디입니다
        elFailureMessage.classList.add('hide');
        elFailureMessageTwo.classList.add('hide');
    }
}

elInputPassword.onkeyup = function () {

    // console.log(elInputPassword.value);
    // 값을 입력한 경우
    if (elInputPassword.value.length !== 0) {
        if(strongPassword(elInputPassword.value)) {
            elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
        }
        else {
            elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
        }
    }
        // 값을 입력하지 않은 경우 (지웠을 때)
    // 모든 메시지를 가린다.
    else {
        elStrongPasswordMessage.classList.add('hide');
    }
};

elInputPasswordRetype.onkeyup = function () {

    // console.log(elInputPasswordRetype.value);
    if (elInputPasswordRetype.value.length !== 0) {
        if(isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
            elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
        }
        else {
            elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
        }
    }
    else {
        elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
    }
};