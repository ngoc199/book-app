# Ứng dụng đọc sách (API)
## Cài đặt
Tải server khuyến khích sử dụng Git. Cài đặt Git tại [trang chủ](https://git-scm.com/downloads) nếu chưa có.

**Phần dưới đây sẽ hướng dẫn sử dụng Git. Ai biết rồi thì bỏ qua nhé~ :wink:**

Tải server về:
`git clone https://github.com/ngoc199/book-app.git`

Sau khi chỉnh sửa code, tải server lên theo các bước sau:
1. Đưa toàn bộ file vào stage:
`git add .`
2. Xác nhận thay đổi:
`git commit -m 'Mô tả ngắn gọn thay đổi'`
3. Cập nhật lên Github:
`git push origin master`

## Các đường dẫn trong API
| URL | Phương thức | Mô tả | Trả về (dạng JSON) | Hoàn thành |
|-|-|-|-|-|
| /users| POST | Cấp quyền truy cập người dùng thông qua Facebook. Cần gửi access_token nhận được từ API Facebook lên API này để xin cấp quyền truy cập. Hệ thống sẽ tự tạo tài khoản nếu người dùng chưa có tài khoản trên hệ thống. | JWT. Cần gắn mã này vào trong phần header ở trong mỗi request yêu cầu quyền truy cập. |:white_check_mark:  |
| /users/favorites/?user_token | POST | Cập nhật sách vào danh sách yêu thích của người dùng. Nếu sách đã có trong danh sách sẽ được loại bỏ, ngược lại thêm mới. Payload: bookId | Http Status |:white_check_mark:  |
| /users/favorites?user_token | GET | Lấy danh sách sách yêu thích của người dùng | Danh sách các thông tin sách được lưu bởi người dùng gồm, tên, số lượt thích, không thích, mô tả ngắn gọn, ảnh bìa |:white_check_mark:  |
| /books?name={bookName} | GET | Lấy toàn bộ thông tin sách, không bao gồm file sách. Có tìm kiếm theo tên nếu thêm tham số name | Danh sách các thông tin sách gồm, tên, số lượt thích, không thích, mô tả ngắn gọn, ảnh bìa |:white_check_mark:  |
| /books/{bookId} | GET | Lấy thông tin sách được chọn, bao gồm file sách | Thông tin chi tiết của sách như trên bao gồm cả file sách |:white_check_mark:  |
| /products | GET | Lấy thông tin toàn bộ gói sản phẩm | Danh sách thông tin chi tiết các gói sản phẩm |:white_check_mark:  |
| /products/{productId} | POST | Đặt mua gói sản phẩm | boolean |:black_square_button:  |
| /books/{bookId}/reviews | GET | Lấy danh sách đánh giá của sách | Danh sách đánh giá của sách |:white_check_mark:  |
| /books/{bookId}/reviews?user_token | POST | Thêm đánh giá của người dùng vào sách | Http Status |:white_check_mark:  |
| /reviews/{reviewId}?user_token | PUT | Sửa đánh giá của người dùng vào sách | Http Status |:white_check_mark:  |
| /reviews/{reviewId}?user_token | DELETE | Xóa đánh giá của người dùng trong sách | Http Status |:white_check_mark:  |
| /reactions/{reactionId}/{bookId}/{userToken} | POST | Thêm cảm xúc của người dùng với sách | boolean |:black_square_button:  |
| /reactions/{reactionId}/{bookId}/{userToken} | PUT | Sửa cảm xúc của người dùng với sách | boolean |:black_square_button:  |
| /reactions/{reactionId}/{bookId}/{userToken} | DELETE | Xóa cảm xúc của người dùng với sách | boolean |:black_square_button:  |
| /categories?name={categoryName} | GET | Lấy danh sách danh mục. Có tìm kiếm theo tên nếu thêm tham số name | Danh sách thông tin danh mục |:white_check_mark:  |
| /categories/{categoryId} | GET | Lấy thông tin danh mục | Thông tin chi tiết danh mục |:white_check_mark:  |
