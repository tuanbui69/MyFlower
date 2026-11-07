# 🌸 My Flower - Ứng Dụng Quản Lý & Giới Thiệu Các Loài Hoa

Ứng dụng Android viết bằng ngôn ngữ **Kotlin**, giải quyết trọn vẹn đề bài thực hành/thi lập trình di động Android gồm thiết kế giao diện danh sách, giao diện chi tiết, hộp thoại chỉnh sửa thông tin có kiểm tra hợp lệ dữ liệu (validation) và cập nhật thời gian thực.

---

## 📑 Mục Lục
1. [Tổng Quan Đề Bài & Thang Điểm](#-tổng-quan-đề-bài--thang-điểm)
2. [Các Tính Năng Chính](#-các-tính-năng-chính)
3. [Công Nghệ & Thư Viện Sử Dụng](#-công-nghệ--thư-viện-sử-dụng)
4. [Cấu Trúc Thư Mục Dự Án](#-cấu-trúc-thư-mục-dự-án)
5. [Chi Tiết Lời Giải Từng Câu](#-chi-tiết-lời-giải-từng-câu)
   - [Câu 1: Thiết kế giao diện (2 điểm)](#câu-1-thiết-kế-giao-diện-2-điểm)
   - [Câu 2: Giải thích cấu trúc UI (2 điểm)](#câu-2-giải-thích-cấu-trúc-ui-2-điểm)
   - [Câu 3: Hộp thoại sửa, kiểm tra dữ liệu & lưu (6 điểm)](#câu-3-hộp-thoại-sửa-kiểm-tra-dữ-liệu--lưu-6-điểm)
6. [Hướng Dẫn Cài Đặt & Chạy Ứng Dụng](#-hướng-dẫn-cài-đặt--chạy-ứng-dụng)

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
  - Sẵn 3 loài hoa mẫu: Hoa hướng dương, Hoa hồng đỏ, Hoa sen hồng.
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
│   │   │   │   ├── img_sunflower.xml     # Vector hoa hướng dương
│   │   │   │   ├── img_rose.xml          # Vector hoa hồng đỏ
│   │   │   │   └── img_lotus.xml         # Vector hoa sen hồng
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

## 📖 Chi Tiết Lời Giải Từng Câu

### Câu 1: Thiết kế giao diện (2 điểm)
1. **Giao diện 01 (`activity_main.xml` + `item_flower.xml`)**:
   - Dùng `RecyclerView` đặt toàn màn hình với `clipToPadding="false"`.
   - Mỗi item hoa được bọc trong một `CardView` (`app:cardCornerRadius="12dp"`, `app:cardElevation="4dp"`).
   - Danh sách mặc định nạp sẵn 3 loài hoa:
     1. Hoa hướng dương (100,000 VND - bó).
     2. Hoa hồng đỏ (150,000 VND - bó).
     3. Hoa sen hồng (120,000 VND - bó).
2. **Giao diện 02 (`activity_detail.xml`)**:
   - `ImageView` hiển thị ảnh hoa lớn ở phần trên cùng với `scaleType="centerCrop"`.
   - `ScrollView` chứa khối văn bản thông tin chi tiết: tên hoa in đậm, đoạn văn mô tả đầy đủ đặc điểm, giá tiền màu đỏ và đơn vị tính.
   - Nút `Button` "Quay lại" bo tròn pill-shape (`bg_rounded_button.xml`) ở chân màn hình, gọi `finish()` khi nhấn để trở về danh sách.

---

### Câu 2: Giải thích cấu trúc UI (2 điểm)

#### 1. Nguyên lý xây dựng bố cục:
- **`RecyclerView` + `Adapter` + `ViewHolder`**: Giúp quản lý danh sách hiệu quả. Thay vì tạo mới hàng loạt View khi người dùng cuộn (như `ScrollView` thông thường), `RecyclerView` tái sử dụng (recycle) các View đã cuộn ra khỏi màn hình, tránh tràn bộ nhớ RAM và giúp cuộn mượt mà ở tốc độ 60-120 FPS.
- **`CardView`**: Cung cấp đường viền bo góc thẩm mỹ và bóng đổ nổi (elevation) giúp các mục hoa phân tách trực quan, rõ ràng trên nền xám nhạt (`@color/bg_screen`).
- **`ConstraintLayout`**: Giảm số lượng ViewGroup lồng nhau (flatten view hierarchy), cho phép neo vị trí các phần tử linh hoạt theo ràng buộc tương đối (ví dụ: ghim CheckBox sang góc phải ngang hàng với Tên hoa).
- **`ViewBinding`**: Thay thế toàn bộ `findViewById`, loại bỏ nguy cơ `NullPointerException` khi truy cập sai ID hoặc sai kiểu dữ liệu.

#### 2. Vai trò của từng View thành phần:
- `ImageView`: Hiển thị hình ảnh loài hoa với thuộc tính `android:scaleType="centerCrop"` nhằm lấp đầy khung hình mà không làm biến dạng tỷ lệ ảnh gốc.
- `TextView`: Trình bày thông tin phân cấp thị giác theo cỡ chữ (`sp`), độ đậm (`bold`) và màu sắc (`@color/price_red` làm nổi bật giá bán).
- `CheckBox`: Cho phép người dùng đánh dấu chọn hoặc bỏ chọn độc lập từng bông hoa. Được quản lý trạng thái biến `isSelected` trong Model dữ liệu.
- `EditText`: Các ô nhập dữ liệu trong hộp thoại, hỗ trợ chỉ định kiểu bàn phím tương ứng (`numberDecimal`, `textCapSentences`, `textMultiLine`).
- `Button`: Nút thao tác kích hoạt các hàm logic: "Lưu", "Hủy", "Chi tiết" và "Quay lại".

---

### Câu 3: Hộp thoại sửa, kiểm tra dữ liệu & lưu (6 điểm)

#### 3.1. Hiển thị hộp thoại khi nhấn vào mục (2 điểm)
- Trong `FlowerAdapter`, bắt sự kiện click vào thẻ `cardFlower`:
  ```kotlin
  binding.cardFlower.setOnClickListener {
      onItemClick(flower, position)
  }
  ```
- Trong `MainActivity`, hàm `showEditDialog(flower, position)` khởi tạo `AlertDialog` từ layout `dialog_edit_flower.xml` và điền sẵn dữ liệu:
  - Tên hoa hiện tại ➔ `dialogBinding.edtFlowerName.setText(flower.name)`
  - Giá hoa hiện tại ➔ `dialogBinding.edtFlowerPrice.setText(...)`
  - Mô tả hoa hiện tại ➔ `dialogBinding.edtFlowerDesc.setText(flower.shortDesc)`

#### 3.2. Kiểm tra thông tin nhập vào (Validation - 2 điểm)
Khi người dùng bấm nút **"Lưu"**, logic kiểm tra được kích hoạt:
```kotlin
val inputName = dialogBinding.edtFlowerName.text.toString().trim()
val inputPriceStr = dialogBinding.edtFlowerPrice.text.toString().trim()
val inputDesc = dialogBinding.edtFlowerDesc.text.toString().trim()

// 1. Kiểm tra Tên hoa không được rỗng
if (inputName.isEmpty()) {
    dialogBinding.edtFlowerName.error = "Vui lòng nhập tên hoa!"
    dialogBinding.edtFlowerName.requestFocus()
    return@setOnClickListener
}

// 2. Kiểm tra Giá hoa bắt buộc là kiểu số và lớn hơn 0
if (inputPriceStr.isEmpty()) {
    dialogBinding.edtFlowerPrice.error = "Vui lòng nhập giá hoa!"
    dialogBinding.edtFlowerPrice.requestFocus()
    return@setOnClickListener
}
val inputPrice = inputPriceStr.toDoubleOrNull()
if (inputPrice == null || inputPrice <= 0) {
    dialogBinding.edtFlowerPrice.error = "Giá hoa phải là kiểu số hợp lệ và lớn hơn 0!"
    dialogBinding.edtFlowerPrice.requestFocus()
    return@setOnClickListener
}

// 3. Kiểm tra Mô tả không được rỗng
if (inputDesc.isEmpty()) {
    dialogBinding.edtFlowerDesc.error = "Vui lòng nhập mô tả hoa!"
    dialogBinding.edtFlowerDesc.requestFocus()
    return@setOnClickListener
}
```

#### 3.3. Lưu và cập nhật vào mục đã chọn trên Giao diện 01 (2 điểm)
- Dữ liệu mới được cập nhật vào đối tượng `Flower`:
  ```kotlin
  flower.name = inputName
  flower.price = inputPrice
  flower.shortDesc = inputDesc
  flower.detailDesc = "Đặc điểm: $inputName. $inputDesc"

  // Cập nhật lại duy nhất phần tử vừa sửa trên RecyclerView
  flowerAdapter.notifyItemChanged(position)

  Toast.makeText(this, "Đã cập nhật \"$inputName\" thành công!", Toast.LENGTH_SHORT).show()
  dialog.dismiss()
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
