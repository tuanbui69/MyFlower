# 🌸 My Flower - Ứng Dụng Quản Lý & Giới Thiệu Các Loài Hoa

Ứng dụng Android viết bằng ngôn ngữ **Kotlin**, giải quyết trọn vẹn đề bài thực hành/thi lập trình di động Android gồm thiết kế giao diện danh sách, giao diện chi tiết, hộp thoại chỉnh sửa thông tin có kiểm tra hợp lệ dữ liệu (validation) và cập nhật thời gian thực.

---

## 📑 Mục Lục
1. [Tổng Quan Đề Bài & Thang Điểm](#-tổng-quan-đề-bài--thang-điểm)
2. [Các Tính Năng Chính](#-các-tính-năng-chính)
3. [Công Nghệ & Thư Viện Sử Dụng](#-công-nghệ--thư-viện-sử-dụng)
4. [Cấu Trúc Thư Mục Dự Án](#-cấu-trúc-thư-mục-dự-án)
5. [Hướng Dẫn Cài Đặt & Chạy Ứng Dụng](#-hướng-dẫn-cài-đặt--chạy-ứng-dụng)

---

## 🎯 Tổng Quan Đề Bài & Thang Điểm

| Câu hỏi | Điểm | Yêu cầu chính |
| :--- | :---: | :--- |
| **Câu 1** | **2.0đ** | • **Giao diện 01**: Danh sách có ít nhất 3 thành phần (item card) hoa như hình mẫu.<br>• **Giao diện 02**: Màn hình xem chi tiết đầy đủ của một loài hoa, có nút "Quay lại". |
| **Câu 2** | **2.0đ** | Giải thích cách tổ chức, cấu trúc giao diện và vai trò của từng thành phần UI bên trong. |
| **Câu 3** | **6.0đ** | • **3.1 (2đ)**: Nhấn vào 1 mục trên GD 01 hiển thị hộp thoại sửa tên, giá và mô tả.<br>• **3.2 (2đ)**: Kiểm tra thông tin nhập vào hộp thoại, **đảm bảo giá là kiểu số**.<br>• **3.3 (2đ)**: Lưu thông tin vừa sửa vào thành phần đã chọn trên Giao diện 01. |

---

## 🌟 Các Tính Năng Chính

- **Giao diện danh sách hoa (Giao diện 01)**:
  - Hiển thị danh sách hoa dưới dạng thẻ `CardView` bo góc đổ bóng chuẩn Material Design.
  - Mỗi thẻ gồm: Ảnh hoa, Tên hoa, CheckBox chọn/bỏ chọn, Mô tả ngắn, Giá tiền nổi bật màu đỏ (`VND`), Đơn vị tính ("bó").
- **Giao diện chi tiết hoa (Giao diện 02)**:
  - Ảnh hoa lớn toàn chiều ngang ở phần đầu màn hình.
  - Khối nội dung cuộn mượt mà (`ScrollView`) chứa tên hoa, khối mô tả đặc điểm chi tiết, giá tiền và đơn vị.
  - Nút **"Quay lại"** hình viên thuốc (pill-shape) màu xanh lam ở dưới cùng, bấm vào sẽ quay về màn hình trước.
- **Hộp thoại Chỉnh sửa & Kiểm tra (Dialog)**:
  - Khi nhấn vào một item trên GD 01, `AlertDialog` tùy chỉnh xuất hiện với dữ liệu hiện tại được điền sẵn.
  - **Kiểm tra hợp lệ chặt chẽ**:
    - Tên không được rỗng.
    - **Giá hoa bắt buộc phải là số hợp lệ (`Double`), không chứa chữ cái, và phải lớn hơn 0**.
    - Mô tả không được để trống.
  - Cập nhật tức thì vào vị trí item đó trên `RecyclerView` (`notifyItemChanged`), không tải lại toàn bộ danh sách.

---

## 🛠 Công Nghệ & Thư Viện Sử Dụng

- **Ngôn ngữ**: Kotlin (100%)
- **Hệ điều hành hỗ trợ**: Android SDK 26 (Android 8.0) đến SDK 37 (Android 15+)
- **Kiến trúc & Tiện ích**:
  - `ViewBinding`: Truy xuất view an toàn về kiểu dữ liệu (type-safe) và an toàn với null (null-safe).
  - `RecyclerView` + `ViewHolder Pattern`: Tối ưu hóa hiệu năng tái sử dụng view và tiết kiệm bộ nhớ RAM.
  - `CardView` + `ConstraintLayout`: Phẳng hóa phân cấp layout, hỗ trợ bo tròn góc và đổ bóng nhẹ.
  - `AlertDialog`: Tạo hộp thoại chỉnh sửa dữ liệu người dùng.
  - `Vector Drawable`: Tối ưu hóa dung lượng ứng dụng với đồ họa vector sắc nét trên mọi mật độ màn hình.

---

## 📂 Cấu Trúc Thư Mục Dự Án

```text
MyFlower/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/myflower/
│   │   │   ├── Flower.kt               # Data model lưu trữ thông tin hoa (Serializable)
│   │   │   ├── FlowerAdapter.kt        # Adapter cho RecyclerView kết nối dữ liệu và View
│   │   │   ├── MainActivity.kt         # Màn hình chính (Giao diện 01) & xử lý Hộp thoại (Câu 3)
│   │   │   └── DetailActivity.kt       # Màn hình chi tiết (Giao diện 02) & nút Quay lại
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── bg_rounded_button.xml # Shape nền bo góc 24dp cho nút "Quay lại"
│   │   │   │   └── img_sunflower.png     # Ảnh hoa hướng dương
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml       # Layout Giao diện 01 (RecyclerView)
│   │   │   │   ├── activity_detail.xml     # Layout Giao diện 02 (Chi tiết hoa)
│   │   │   │   ├── item_flower.xml         # Layout item card hoa (Hình 03)
│   │   │   │   └── dialog_edit_flower.xml  # Layout hộp thoại chỉnh sửa thông tin hoa
│   │   │   └── values/
│   │   │       ├── colors.xml              # Định nghĩa màu sắc (giá đỏ, nút xanh, nền...)
│   │   │       ├── strings.xml             # Tên ứng dụng và chuỗi ký tự
│   │   │       └── themes.xml              # Theme hệ thống Material3
│   │   └── AndroidManifest.xml         # Khai báo MainActivity & DetailActivity
│   └── build.gradle.kts                # Cấu hình build & kích hoạt viewBinding
└── README.md                           # Tài liệu tổng quan dự án
```

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy Ứng Dụng

1. **Mở dự án trong Android Studio**:
   - Chọn `File` -> `Open` -> Trỏ đến thư mục `C:/Users/jimmy/AndroidStudioProjects/MyFlower`.
2. **Đồng bộ Gradle**:
   - Nhấn **Sync Project with Gradle Files**.
3. **Build APK hoặc chạy trên thiết bị**:
   - Chọn máy ảo Android (AVD) hoặc thiết bị thật đã bật USB Debugging.
   - Nhấn nút **Run 'app'** (`Shift + F10`) hoặc chạy lệnh Gradle trong Terminal:
     ```bash
     ./gradlew assembleDebug
     ```
   - File APK tạo ra tại: `app/build/outputs/apk/debug/app-debug.apk`.
