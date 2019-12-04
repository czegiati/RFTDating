// Reusable function to make User from serverUser object.
export function getUserFromServerObject( serverUserObject: any) {
    return {
        id: serverUserObject.user_id + '',
        username: serverUserObject.username,
        email: serverUserObject.email,
        fullname: serverUserObject.full_name,
        birthdate: serverUserObject.birthdate,
        sex: serverUserObject.sex,
        orientation: serverUserObject.sexualOrientation,
        bio: serverUserObject.bio
    };
}