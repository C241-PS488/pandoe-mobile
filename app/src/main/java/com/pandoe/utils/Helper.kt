package com.pandoe.utils


fun generateProfilePictureUrl(name: String): String {
    val formattedName = name.replace(' ', '+')
    return "https://ui-avatars.com/api/?name=$formattedName&uppercase=true&background=random&size=512"
}