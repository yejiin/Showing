from django.db import models

from performance.models import Performance


class Tag(models.Model):
    performance = models.ForeignKey(Performance, on_delete=models.CASCADE)
    tag_name = models.CharField(max_length=255)
    weight = models.FloatField()
    create_date = models.DateTimeField(auto_now_add=True)
    update_date = models.DateTimeField(auto_now=True)

    class Meta:
        managed = False
        db_table = 'tag'
