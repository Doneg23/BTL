# Bài tập lớn OOP - Bomberman Game

Bài tập lớn môn Lập trình Hướng đối tượng
#### Người thực hiện:

- Nguyễn Xuân Anh - 24022257
- Nguyễn Hải Đăng - 24022281
- Nguyễn Đắc Trung Hiếu - 24022329

## Mô tả về các đối tượng trong trò chơi

- ![](src/resources/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
- ![](src/resources/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](src/resources/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](src/resources/sprites/explosion_horizontal.png) được tạo ra.


- ![](src/resources/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](src/resources/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](src/resources/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.


- ![](src/resources/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Buff Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](src/resources/sprites/powerup_speed.png) *PowerUpSpeed* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- ![](src/resources/sprites/powerup_flames.png) *PowerUpFlame* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- ![](src/resources/sprites/powerup_bombs.png) *PowerUpBomb* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.

Các loại *Enemy* sẽ có tốc độ và cách thức hoạt động khác nhau được lượt kê dưới đây:
- ![](src/resources/sprites/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
- ![](src/resources/sprites/oneal_left1.png) *Oneal* có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm và nó sẽ đuổi theo Bomber nếu khoảng cách giữa nó và Bomber nhỏ hơn 5 đơn vị khoảng cách
- ![](src/resources/sprites/doll_left1.png) *Doll* có tốc độ di chuyển chậm, khi nó đối diện với Bomber (hàng ngang hoặc dọc) và giữa nó không có chướng ngại nào (kể cả Bomb) thì nó sẽ tăng độ di chuyển và đuổi theo Bomber
- ![](src/resources/sprites/minvo_left1.png) *Minvo* có tốc độ di chuyển cố định, và nó có thể đi xuyên bomb
- ![](src/resources/sprites/kondoria_left1.png) *Kondoria* có tốc độ di chuyển cố định, nó có thể đi xuyên qua các bricks, và nó luôn luôn đổi theo Bomber

## Mô tả game play, xử lý va chạm và xử lý bom nổ
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm![](src/resources/sprites/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên![](src/resources/sprites/explosion_vertical.png)/dưới![](src/resources/sprites/explosion_vertical.png)/trái![](src/resources/sprites/explosion_horizontal.png)/phải![](src/resources/sprites/explosion_horizontal.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.

## Preview Game
![](src/resources/preview/prv1.png)

## Các thông tin về lập trình
### Công cụ lập trình

- Ngôn ngữ: Java Swing (JDK23), C++
- IDE: IntelliJ - VSCode
### Cây thừa kế cho các đối tượng của Game

![](src/resources/uml/uml1.png)
![](src/resources/uml/uml2.png)
